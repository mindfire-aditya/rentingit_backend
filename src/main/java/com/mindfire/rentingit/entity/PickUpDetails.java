package com.mindfire.rentingit.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PICKUP_DETAILS")
@Data
public class PickUpDetails {

	private Long assetId;

	private int pinCode;

	private String houseNumber;
	private String street;
	private String lane;
	private String landmark;
	private String googleMapLocation;
	private String cityTown;

	public PickUpDetails(Long assetId, int pinCode, String houseNumber, String street, String lane, String landmark,
			String googleMapLocation, String cityTown) {
		super();
		this.assetId = assetId;
		this.pinCode = pinCode;
		this.houseNumber = houseNumber;
		this.street = street;
		this.lane = lane;
		this.landmark = landmark;
		this.googleMapLocation = googleMapLocation;
		this.cityTown = cityTown;
	}
	
	

}
