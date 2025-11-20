package com.media.socialmedia.controllers;

import com.media.socialmedia.models.SocialUser;
import com.media.socialmedia.services.SocialServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {

    private SocialServices socialServices;
    public SocialController(SocialServices socialServices) {
        this.socialServices = socialServices;
    }

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>>getUsers(){
        return new ResponseEntity<>(socialServices.getAllusers(), HttpStatus.OK);
    }

    @PostMapping("/social/saveuser")
    public ResponseEntity<SocialUser>saveUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialServices.saveUser(socialUser), HttpStatus.CREATED);
    }
    @DeleteMapping("/social/deleteuser/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userid){
        socialServices.deleteUser(userid);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }
}