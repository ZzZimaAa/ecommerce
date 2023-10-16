package com.coursework.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@IdClass(UserId.class)
public class UserRole {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_name")
    private String roleName;

    public Integer getUser() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserRole(Integer userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public UserRole() {
    }
}