package com.mindfire.rentingit.entity;

import java.time.LocalDateTime;

public class ReturnTracker {
    private int id;
    private int productId;
    private int ownerId;
    private int customerId;
    private int orderId;

    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;
    private LocalDateTime maintenanceTime;
    private LocalDateTime returnDateTime;

    private boolean returned;
}
