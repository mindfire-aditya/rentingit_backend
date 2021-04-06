package com.mindfire.rentingit.entity;

import java.time.LocalDateTime;

public class OrderDetail {
    private int id;
    private int ownerId;
    private int customerId;
    private int productId;
    private int totalAmount;
    private int returnTrackerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;

    private String rent_mode;
    private String agreementUrl;
}
