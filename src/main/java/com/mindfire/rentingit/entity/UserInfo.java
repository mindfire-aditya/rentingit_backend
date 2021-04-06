package com.mindfire.rentingit.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private int phone;
    private String firstName;
    private String lastName;
    private String houseNo;
    private String street;
    private String lane;
    private String landmark;
    private String district;
    private String state;
    private String idProofType;
    private String idProofUrl;

    private boolean isVerified;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
