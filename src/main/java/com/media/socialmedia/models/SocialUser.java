package com.media.socialmedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "socialUser",cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(name = "social_profile")
    private SocialProfile socialProfile;
//    Cascading
//    ✅ 1. CascadeType.PERSIST
//    Meaning:
//    When you save the parent, the child is also saved (inserted).
//    ✅ 2. CascadeType.MERGE
//    Meaning:
//    When you update the parent, the child is also updated (merged).
//    ✅ 3. CascadeType.REMOVE
//    Meaning:
//    When you delete the parent, the child is also deleted.
//    ✅ 4. CascadeType.ALL
//    Meaning:
//    Applies ALL cascades:
//    PERSIST
//    MERGE
//    REMOVE
//    REFRESH
//    DETACH
//    In short:
//    ✔ Save parent → child saved
//    ✔ Update parent → child updated
//    ✔ Delete parent → child deleted
    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts= new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
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
