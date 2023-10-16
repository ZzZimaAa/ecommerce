package com.coursework.ecommerce.model;

import java.io.Serializable;

public class UserId implements Serializable {
    private Integer userId;

    public UserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}