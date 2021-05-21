package com.mindfire.rentingit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ujjwalk
 *
 */
@Entity
@Table(name = "PICKUP_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PickUpDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int assetId;

	private int pinCode;

	private String houseNumber;
	private String street;
	private String lane;
	private String landmark;
	private String googleMapLocation;
	private String cityTown;

	
	

}
