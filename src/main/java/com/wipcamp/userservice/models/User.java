package com.wipcamp.userservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class User {
	@Id
	@GeneratedValue
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
	private char bloodGroup;
	private long telNo;
	private Address address;
	private String religion;
	private String school;
	private int level;
	private double gpax;
	private char shirtSize;
	private Parent parent;
	private long telEmergency;
	private String allergicFood;
	private String congenitalDisease;
	private String congenitalDrug;
	private Major major;

	public User(long id, long lineId, long facebookId, String firstName, String lastName, String firstNameEn, String lastNameEn,
			String nickName, Date birthDate, long citizenId, String gender, char bloodGroup, long telNo, Address address,
			String religion, String school, int level, double gpax, char shirtSize, Parent parent, long telEmergency,
			String allergicFood, String congenitalDisease, String congenitalDrug) {
		this.id = id;
		this.lineId = lineId;
		this.facebookId = facebookId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.firstNameEn = firstNameEn;
		this.lastNameEn = lastNameEn;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.citizenId = citizenId;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.telNo = telNo;
		this.address = address;
		this.religion = religion;
		this.school = school;
		this.level = level;
		this.gpax = gpax;
		this.shirtSize = shirtSize;
		this.parent = parent;
		this.telEmergency = telEmergency;
		this.allergicFood = allergicFood;
		this.congenitalDisease = congenitalDisease;
		this.congenitalDrug = congenitalDrug;
	}

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

	public char getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(char bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public long getTelNo() {
		return telNo;
	}

	public void setTelNo(long telNo) {
		this.telNo = telNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public char getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(char shirtSize) {
		this.shirtSize = shirtSize;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
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
