package com.wipcamp.userservice.models;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class Question {
	@Id
	@GeneratedValue
	private int id;

	@NonNull
	private String name;

	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;
}
