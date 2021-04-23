package com.mindfire.rentingit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_INFO")
public class UserDetailsInfo {

	@Id
	private long Id;
	
	private String firstName;
	private String lastName;
	private long phoneNo;
	private int houseNo;
	private int streetNo;
	private String lane;
	private String district;
	private String state;
	private String landmark;
	private String city;
	private int pincode;
	private String idProofType;
	private String idNumber;

	
	
	
	public UserDetailsInfo() {

	}

	public UserDetailsInfo(String firstName, String lastName, long phoneNo, int houseNo, int streetNo,
			String lane, String district, String state, String landmark, String city, int pincode, String idProofType,
			String idNumber,long id) {

		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.lane = lane;
		this.district = district;
		this.state = state;
		this.landmark = landmark;
		this.city = city;
		this.pincode = pincode;
		this.idProofType = idProofType;
		this.idNumber = idNumber;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public int getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}
