package com.coursework.ecommerce.repository;

import com.coursework.ecommerce.model.AuthenticationToken;
import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findProfileByUserId(Integer userId);
}