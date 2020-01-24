package com.wipcamp.userservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@SequenceGenerator(name="wipId_generator", sequenceName = "wipId_sequence", initialValue = 120000, allocationSize = 1)
@Data
@NoArgsConstructor
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
	private String telNo;
	@Column(nullable = true)
	private String religion;
	@Column(nullable = true)
	private String school;
	@Column(nullable = true)
	private String schoolMajor;
	@Column(nullable = true)
	private String level;
	@Column(nullable = true)
	private String telEmergency;
	@Column(nullable = true)
	private Double gpax;
	@Column(nullable = true)
	private String allergicFood;
	@Column(nullable = true)
	private String congenitalDisease;
	@Column(nullable = true)
	private String congenitalDrug;

	@CreationTimestamp
	@JsonFormat(timezone = "GMT+07:00")
	private Timestamp createdAt;
	@CreationTimestamp
	@JsonFormat(timezone = "GMT+07:00")
	private Timestamp updatedAt;



	@OneToOne
	@JoinColumn(name="address_id",referencedColumnName = "id")
	private Address address;

	@OneToOne
	@JoinColumn(name="parent_id",referencedColumnName = "id")
	private Parent parent;

	@OneToOne
	@JoinColumn(name="general_answer_id",referencedColumnName = "id")
	private GeneralAnswer generalAnswer;

	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;

	@OneToMany(mappedBy = "user")
	private List<Answer> answerList;

	@Column(nullable = true)
	private String status;

	public void setId(long wipId){
		this.wipId = wipId;
	}
}
