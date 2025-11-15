package com.media.socialmedia;

import com.media.socialmedia.models.Post;
import com.media.socialmedia.models.SocialGroup;
import com.media.socialmedia.models.SocialProfile;
import com.media.socialmedia.models.SocialUser;
import com.media.socialmedia.repositories.PostRepository;
import com.media.socialmedia.repositories.SocialGroupRepository;
import com.media.socialmedia.repositories.SocialProfileRepository;
import com.media.socialmedia.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private final SocialUserRepository socialuserRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository socialuserRepository, SocialGroupRepository groupRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.socialuserRepository = socialuserRepository;
        this.groupRepository = groupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData(){
        return args -> {
            //create some users
            SocialUser user1=new SocialUser();
            SocialUser user2=new SocialUser();
            SocialUser user3=new SocialUser();

            //Save users to the database
            socialuserRepository.save(user1);
            socialuserRepository.save(user2);
            socialuserRepository.save(user3);

            //create some groups
            SocialGroup group1=new SocialGroup();
            SocialGroup group2=new SocialGroup();

            //add users to groups
            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user2);
            group2.getSocialUsers().add(user3);

            //save groups to database
            groupRepository.save(group1);
            groupRepository.save(group2);

            //Associate users with groups
            user1.getSocialGroups().add(group1);
            user2.getSocialGroups().add(group1);
            user2.getSocialGroups().add(group2);
            user3.getSocialGroups().add(group2);
            //Save users back to database to update associations
            socialuserRepository.save(user1);
            socialuserRepository.save(user2);
            socialuserRepository.save(user3);
            //create some posts
            Post post1=new Post();
            Post post2=new Post();
            Post post3=new Post();
            //Associate posts with users
            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);
            //save posts to the database
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            // Create some social profiles
            SocialProfile profile1=new SocialProfile();
            SocialProfile profile2=new SocialProfile();
            SocialProfile profile3=new SocialProfile();
            // Associate profiles with users
            profile1.setSocialUser(user1);
            profile1.setSocialUser(user2);
            profile1.setSocialUser(user3);
            // Save profiles to the database (assuming you have a SocialProfileRepository)
            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);
        };
    }
}
