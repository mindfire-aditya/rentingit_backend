package com.mindfire.rentingit.entity;

import java.time.LocalDateTime;

public class Asset {
    private int id;
    private int categoryId;
    private int ownerId;
    private int units;
    private int pricePerHour;
    private int pricePerDay;
    private int pricePerWeek;
    private int pricePerMonth;

    private String productName;
    private String assetSatus;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime maintainanceTime;
}
