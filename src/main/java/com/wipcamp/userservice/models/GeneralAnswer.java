package com.wipcamp.userservice.models;

import lombok.Data;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "general_answers")
@Data
public class GeneralAnswer {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = true, length=1024)
	private String firstAnswer;

	@Column(nullable = true, length=1024)
	private String secondAnswer;

	@Column(nullable = true, length=1024)
	private String thirdAnswer;

	@Column(nullable = true, length=1024)
	private String forthAnswer;
}
