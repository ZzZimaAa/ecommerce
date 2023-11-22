package com.coursework.ecommerce.service;

import com.coursework.ecommerce.model.User;
import com.coursework.ecommerce.model.UserRole;
import com.coursework.ecommerce.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public void createUserRole (Integer id, String roleName) {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setRoleName(roleName);
        userRoleRepository.save(userRole);
    }
}