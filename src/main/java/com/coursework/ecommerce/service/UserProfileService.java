package com.coursework.ecommerce.service;

import com.coursework.ecommerce.dto.user.SignupDto;
import com.coursework.ecommerce.dto.user.UserCreateDto;
import com.coursework.ecommerce.model.AuthenticationToken;
import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserProfile;
import com.coursework.ecommerce.model.UserRole;
import com.coursework.ecommerce.repository.UserProfileRepository;
import com.coursework.ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    UserProfileRepository userProfileRepository;

    public void createUserProfile (SignupDto signupDto, User user, UserRole role) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstName(signupDto.getFirstName());
        userProfile.setLastName(signupDto.getLastName());
        userProfile.setUserRole(role);
        userProfileRepository.save(userProfile);
    }

    public void createUserProfileCD (UserCreateDto userCreateDto, User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstName(userCreateDto.getFirstName());
        userProfile.setLastName(userCreateDto.getLastName());
        userProfile.setUserRole(userCreateDto.getRole());
        userProfileRepository.save(userProfile);
    }

    public UserRole getUserProf(User user) {
        try {
            UserProfile userProfile = userProfileRepository.findProfileByUserId(user.getId());
            if (Helper.notNull(userProfile) && Helper.notNull(userProfile.getUserRole())) {
                return userProfile.getUserRole();
            }
        } catch (Exception e) {
            // Handle the exception (log or throw a more specific exception)
            e.printStackTrace(); // Example: Log the exception
        }
        return null;
    }
}