package com.mindfire.rentingit.dto.request;

import java.time.LocalDateTime;

public class OrderRequest {
	
	private int ownerId;
	private int productId;
	private int customerId;
	private String rent_mode;
	private int units;
	private LocalDateTime start_datetime;
	private LocalDateTime end_datetime;
	private boolean agreed_to_terms_and_conditions;
	private int total_amount;
	
	
	public OrderRequest() {
		super();
	}
	
	public OrderRequest(int ownerId, int productId, int customerId, String rent_mode, int units,
			LocalDateTime start_datetime, LocalDateTime end_datetime, boolean agreed_to_terms_and_conditions,
			int total_amount) {
		super();
		this.ownerId = ownerId;
		this.productId = productId;
		this.customerId = customerId;
		this.rent_mode = rent_mode;
		this.units = units;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
		this.agreed_to_terms_and_conditions = agreed_to_terms_and_conditions;
		this.total_amount = total_amount;
	}

	public String getRent_mode() {
		return rent_mode;
	}
	public void setRent_mode(String rent_mode) {
		this.rent_mode = rent_mode;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public LocalDateTime getStart_datetime() {
		return start_datetime;
	}
	public void setStart_datetime(LocalDateTime start_datetime) {
		this.start_datetime = start_datetime;
	}
	public LocalDateTime getEnd_datetime() {
		return end_datetime;
	}
	public void setEnd_datetime(LocalDateTime end_datetime) {
		this.end_datetime = end_datetime;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public boolean isAgreed_to_terms_and_conditions() {
		return agreed_to_terms_and_conditions;
	}

	public void setAgreed_to_terms_and_conditions(boolean agreed_to_terms_and_conditions) {
		this.agreed_to_terms_and_conditions = agreed_to_terms_and_conditions;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	
	
}
