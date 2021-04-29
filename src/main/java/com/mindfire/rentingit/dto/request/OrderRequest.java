package com.mindfire.rentingit.dto.request;

import java.time.LocalDateTime;

public class OrderRequest {
	
	String rent_mode;
	int units;
	LocalDateTime start_datetime;
	LocalDateTime end_datetime;
	boolean terms_and_conditions;
	
	
	public OrderRequest() {
		super();
	}
	public OrderRequest(String rent_mode, int units, LocalDateTime start_datetime, LocalDateTime end_datetime,
			boolean terms_and_conditions) {
		super();
		this.rent_mode = rent_mode;
		this.units = units;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
		this.terms_and_conditions = terms_and_conditions;
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
	public boolean isTerms_and_conditions() {
		return terms_and_conditions;
	}
	public void setTerms_and_conditions(boolean terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}
	
	

}
