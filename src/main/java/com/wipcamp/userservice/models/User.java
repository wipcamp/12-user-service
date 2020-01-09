package com.wipcamp.userservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@SequenceGenerator(name="wipId_generator", sequenceName = "wipId_sequence", initialValue = 120000, allocationSize = 1)
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "wipId_generator")
	@Column(name = "wip_id")
	private long wipId;

	private Long lineId;
	@Column(nullable = true)
	private Long facebookId;

	@Column(nullable = true)
	private String firstName;
	@Column(nullable = true)
	private String lastName;

	@Column(nullable = true)
	private String firstNameEn;
	@Column(nullable = true)
	private String lastNameEn;
	@Column(nullable = true)
	private String nickName;



	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private Date birthDate;

	@Column(nullable = true)
	private String citizenId;

	@Column(nullable = true)
	private String gender;
	@Column(nullable = true)
	private String bloodGroup;
	@Column(nullable = true)
	private Long telNo;
	@Column(nullable = true)
	private String religion;
	@Column(nullable = true)
	private String school;
	@Column(nullable = true)
	private String schoolMajor;
	@Column(nullable = true)
	private String level;
	@Column(nullable = true)
	private Long telEmergency;
	@Column(nullable = true)
	private Double gpax;
	@Column(nullable = true)
	private String allergicFood;
	@Column(nullable = true)
	private String congenitalDisease;
	@Column(nullable = true)
	private String congenitalDrug;

	@OneToOne
	@JoinColumn(name="address_id",referencedColumnName = "id")
	private Address address;

	@OneToOne
	@JoinColumn(name="parent_id",referencedColumnName = "id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;

	@OneToMany(mappedBy = "user")
	private List<Answer> answerList;

	public long getWipId() {
		return wipId;
	}

	public void setWipId(long wipId) {
		this.wipId = wipId;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Long facebookId) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getTelNo() {
		return telNo;
	}

	public void setTelNo(Long telNo) {
		this.telNo = telNo;
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

	public String getSchoolMajor() {
		return schoolMajor;
	}

	public void setSchoolMajor(String schoolMajor) {
		this.schoolMajor = schoolMajor;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getTelEmergency() {
		return telEmergency;
	}

	public void setTelEmergency(Long telEmergency) {
		this.telEmergency = telEmergency;
	}

	public Double getGpax() {
		return gpax;
	}

	public void setGpax(Double gpax) {
		this.gpax = gpax;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
}
