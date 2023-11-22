package com.coursework.ecommerce.dto.user;

import com.coursework.ecommerce.model.UserRole;

public class UserUpdateDto {
    // skipping updating passord as of now
    private Integer id;
    private String firstName;
    private String lastName;
    private UserRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}