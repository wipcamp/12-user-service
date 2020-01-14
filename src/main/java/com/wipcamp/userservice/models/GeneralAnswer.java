package com.wipcamp.userservice.models;

import lombok.Data;

import org.springframework.lang.NonNull;

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

	private String firstAnswer;

	private String secondAnswer;
}
