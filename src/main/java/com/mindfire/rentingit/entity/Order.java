/*
 * @author Ujjwal Kumar
 */
package com.mindfire.rentingit.entity;

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
	private String rentStartDate;
	private String rentEndDate;
	private String rentMode;
	private String agreementUrl;
	private int totalAmount;

	public Order() {
	}

	public Order(Long id, String rentStartDate, String rentEndDate, String rentMode, String agreementUrl,
			int totalAmount) {
		super();
		this.Id = id;
		this.rentStartDate = rentStartDate;
		this.rentEndDate = rentEndDate;
		this.rentMode = rentMode;
		this.agreementUrl = agreementUrl;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(String rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public String getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(String rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public String getRentMode() {
		return rentMode;
	}

	public void setRentMode(String rentMode) {
		this.rentMode = rentMode;
	}

	public String getAgreementUrl() {
		return agreementUrl;
	}

	public void setAgreementUrl(String agreementUrl) {
		this.agreementUrl = agreementUrl;
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

}
