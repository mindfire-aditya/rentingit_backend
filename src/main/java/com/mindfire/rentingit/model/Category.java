package com.mindfire.rentingit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String parentCategory;
	private String childCategory;
	private Long parentCategoryId;

	public Category() {
	}

	public Category(Long id, String parentCategory, String childCategory, Long parentCategoryId) {
		super();
		Id = id;
		this.parentCategory = parentCategory;
		this.childCategory = childCategory;
		this.parentCategoryId = parentCategoryId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

}
