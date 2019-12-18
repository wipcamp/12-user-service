package com.wipcamp.userservice.models;

import java.sql.Date;

public class User {
	private long id;
	private long lineId;
	private long facebookId;
	private String firstName;
	private String lastName;
	private String firstNameEn;
	private String lastNameEn;
	private String nickName;
	private Date birthDate;
	private long citizenId;
	private String gender;
	private long telNo;
	private String addrProvince;
	private String addrDistict;
	private char bloodGroup;
	private String religion;
	private String school;
	private int level;
	private double gpax;
	private char size;
	private String major;
	private String telParent;
	private String parentRelation;
	private long telEmergency;
	private String allergicFood;
	private String congenitalDisease;
	private String congenitalDrug;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLineId() {
		return lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	public long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(long facebookId) {
		this.facebookId = facebookId;
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

	public String getFirstNameEn() {
		return firstNameEn;
	}

	public void setFirstNameEn(String firstNameEn) {
		this.firstNameEn = firstNameEn;
	}

	public String getLastNameEn() {
		return lastNameEn;
	}

	public void setLastNameEn(String lastNameEn) {
		this.lastNameEn = lastNameEn;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public long getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(long citizenId) {
		this.citizenId = citizenId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getTelNo() {
		return telNo;
	}

	public void setTelNo(long telNo) {
		this.telNo = telNo;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrDistict() {
		return addrDistict;
	}

	public void setAddrDistict(String addrDistict) {
		this.addrDistict = addrDistict;
	}

	public char getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(char bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getGpax() {
		return gpax;
	}

	public void setGpax(double gpax) {
		this.gpax = gpax;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTelParent() {
		return telParent;
	}

	public void setTelParent(String telParent) {
		this.telParent = telParent;
	}

	public String getParentRelation() {
		return parentRelation;
	}

	public void setParentRelation(String parentRelation) {
		this.parentRelation = parentRelation;
	}

	public long getTelEmergency() {
		return telEmergency;
	}

	public void setTelEmergency(long telEmergency) {
		this.telEmergency = telEmergency;
	}

	public String getAllergicFood() {
		return allergicFood;
	}

	public void setAllergicFood(String allergicFood) {
		this.allergicFood = allergicFood;
	}

	public String getCongenitalDisease() {
		return congenitalDisease;
	}

	public void setCongenitalDisease(String congenitalDisease) {
		this.congenitalDisease = congenitalDisease;
	}

	public String getCongenitalDrug() {
		return congenitalDrug;
	}

	public void setCongenitalDrug(String congenitalDrug) {
		this.congenitalDrug = congenitalDrug;
	}
}
