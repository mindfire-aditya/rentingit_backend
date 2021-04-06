package com.mindfire.rentingit.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="parent_category_id")
    private int parentCategoryId;

    @Column(name="subcategory_id")
    private int subCategoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="subcategory_name")
    private String subCategoryName;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
