package com.media.socialmedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "socialUser",cascade = CascadeType.ALL)
    //@JoinColumn(name = "social_profile")
    private SocialProfile socialProfile;
    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts= new ArrayList<>();

    @ManyToMany
    private Set<SocialGroup> socialGroups=new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;

        if (socialProfile.getSocialUser() != this) { // avoid loop
            socialProfile.setSocialUser(this);
        }
    }

}
