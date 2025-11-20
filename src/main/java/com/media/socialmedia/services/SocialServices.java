package com.media.socialmedia.services;

import com.media.socialmedia.models.SocialUser;
import com.media.socialmedia.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialServices {
    @Autowired
    SocialUserRepository socialUserRepository;
    public List<SocialUser> getAllusers() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    public SocialUser deleteUser(Long userid) {
        SocialUser socialUser=socialUserRepository.findById(userid).orElseThrow(()-> new RuntimeException("User not found"));
        socialUserRepository.delete(socialUser);
        return socialUser;
    }
}
