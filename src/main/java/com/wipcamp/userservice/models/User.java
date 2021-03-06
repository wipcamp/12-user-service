package com.wipcamp.userservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.wipcamp.userservice.seeders.SeederRunner;

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
import javax.persistence.Lob;
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
@SequenceGenerator(name="wipId_generator", sequenceName = "wipId_sequence", initialValue = 120001, allocationSize = 1)
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "wipId_generator")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "wip_id")
	private long wipId;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(unique = true)
	private String lineId;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
	private String telEmergency;
	@Column(nullable = true)
	private String allergicFood;
	@Column(nullable = true)
	private String congenitalDisease;
	@Column(nullable = true)
	private String congenitalDrug;

	@Lob
	@Column(nullable = true, length=9999)
	private String computerWorks;

	@Column(nullable = true)
	private String province;

	@Column(nullable = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String uploadDocumentPath;

	@OneToOne
	@JoinColumn(name="school_id",referencedColumnName = "id")
	private School school;

	@OneToOne
	@JoinColumn(name="parent_id",referencedColumnName = "id")
	private Parent parent;

	@OneToOne
	@JoinColumn(name="general_answer_id",referencedColumnName = "id")
	private GeneralAnswer generalAnswer;

	@OneToOne
	@JoinColumn(name="user_status_id",referencedColumnName = "id")
	private UserStatus userStatus;

	@OneToOne
	@JoinColumn(name="user_know_whence",referencedColumnName = "id")
	private KnowWhence knowWhence;

	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;

	@OneToMany(mappedBy = "user")
	private List<Answer> answerList;


	@CreationTimestamp
	@JsonFormat(timezone = "GMT+07:00")
	@Column(updatable = false)
	private Timestamp createdAt;
	@UpdateTimestamp
	@JsonFormat(timezone = "GMT+07:00")
	private Timestamp updatedAt;

	public void setId(long wipId){
		this.wipId = wipId;
	}
}
