package com.wipcamp.userservice.models;

import lombok.Data;

import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_status")
@Data
@NoArgsConstructor
public class UserStatus {

	@Id
	@GeneratedValue
	private int id;

	private boolean isAccepted = false;

	private boolean isRegistered = false;

	private boolean isGeneralAnswered = false;

	private boolean isMajorAnswered = false;

	private boolean isSubmitted = false;


}
