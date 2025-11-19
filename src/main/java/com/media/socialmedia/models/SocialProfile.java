package com.media.socialmedia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToOne
    @JoinColumn(name = "social_user")
    @JsonIgnore
    private SocialUser socialUser;

    public void setSocialUser(SocialUser socialUser) {
        this.socialUser = socialUser;

        if (socialUser.getSocialProfile() != this) { // avoid loop
            socialUser.setSocialProfile(this);
        }
    }

}
