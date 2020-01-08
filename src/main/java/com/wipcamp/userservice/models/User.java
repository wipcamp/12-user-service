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
}
