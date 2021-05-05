/*
 * @author Ujjwal Kumar
 */
package com.mindfire.rentingit.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAILS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private int ownerId;
	private int customerId;
	private int productId;
	private LocalDateTime rentStartDate;
	private LocalDateTime rentEndDate;
	private String rentMode;
	private boolean agreedToTermsAndConditions;
	private int totalAmount;
	private int units;

	public Order() {
	}

	public Order(int ownerId, int customerId, int productId, LocalDateTime rentStartDate, LocalDateTime rentEndDate,
			String rentMode, boolean agreedToTermsAndConditions, int totalAmount,int units) {
		super();
		this.ownerId = ownerId;
		this.customerId = customerId;
		this.productId = productId;
		this.rentStartDate = rentStartDate;
		this.rentEndDate = rentEndDate;
		this.rentMode = rentMode;
		this.agreedToTermsAndConditions = agreedToTermsAndConditions;
		this.totalAmount = totalAmount;
		this.units = units;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public LocalDateTime getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(LocalDateTime rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public LocalDateTime getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(LocalDateTime rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public String getRentMode() {
		return rentMode;
	}

	public void setRentMode(String rentMode) {
		this.rentMode = rentMode;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean isAgreedToTermsAndConditions() {
		return agreedToTermsAndConditions;
	}

	public void setAgreedToTermsAndConditions(boolean agreedToTermsAndConditions) {
		this.agreedToTermsAndConditions = agreedToTermsAndConditions;
	}
	

	@Override
	public String toString() {
		return "Order [Id=" + Id + ", ownerId=" + ownerId + ", customerId=" + customerId + ", productId=" + productId
				+ ", rentStartDate=" + rentStartDate + ", rentEndDate=" + rentEndDate + ", rentMode=" + rentMode
				+ ", agreedToTermsAndConditions=" + agreedToTermsAndConditions + ", totalAmount=" + totalAmount + ",units = "+ units +"]";
	}

}
