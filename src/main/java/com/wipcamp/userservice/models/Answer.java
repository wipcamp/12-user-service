package com.wipcamp.userservice.models;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private int id;

	@NonNull
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;

	@NonNull
	@ManyToOne
	@JoinColumn(name="question_id",referencedColumnName = "id")
	private Question question;


	@NonNull
	private String answerContent;
}
