package com.mindfire.rentingit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASSETS")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String productName;
	private String maintainanceTime;
	private String assetStatus;
	private String assetDescription;
	private String imageUrl;
	private int units;
	private int pricePerHour;
	private int pricePerDay;
	private int pricePerWeek;
	private int pricePerMonth;
	private int pinCode;

	public Product() {
	}

	public Product(Long id, String productName, String maintainanceTime, String assetStatus, String assetDescription,
			String imageUrl, int units, int pricePerHour, int pricePerDay, int pricePerWeek, int pricePerMonth,
			int pinCode) {
		super();
		Id = id;
		this.productName = productName;
		this.maintainanceTime = maintainanceTime;
		this.assetStatus = assetStatus;
		this.assetDescription = assetDescription;
		this.imageUrl = imageUrl;
		this.units = units;
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.pricePerWeek = pricePerWeek;
		this.pricePerMonth = pricePerMonth;
		this.pinCode = pinCode;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaintainanceTime() {
		return maintainanceTime;
	}

	public void setMaintainanceTime(String maintainanceTime) {
		this.maintainanceTime = maintainanceTime;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(int pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public int getPricePerWeek() {
		return pricePerWeek;
	}

	public void setPricePerWeek(int pricePerWeek) {
		this.pricePerWeek = pricePerWeek;
	}

	public int getPricePerMonth() {
		return pricePerMonth;
	}

	public void setPricePerMonth(int pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

}
