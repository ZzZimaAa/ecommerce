package com.coursework.ecommerce.service;

import com.coursework.ecommerce.dto.user.SignupDto;
import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserProfile;
import com.coursework.ecommerce.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    public void createUserProfile (SignupDto signupDto, User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user.getId());
        userProfile.setFirstName(signupDto.getFirstName());
        userProfile.setLastName(signupDto.getLastName());
        userProfileRepository.save(userProfile);
    }
}