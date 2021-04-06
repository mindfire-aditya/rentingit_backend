package com.mindfire.rentingit.entity;

import javax.persistence.Column;

public class UserRole {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
