package com.media.socialmedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "socialUser")
    //@JoinColumn(name = "social_profile")
    private SocialProfile socialProfile;
    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts= new ArrayList<>();

    @ManyToMany
    private Set<SocialGroup> socialGroups=new HashSet<>();
}
