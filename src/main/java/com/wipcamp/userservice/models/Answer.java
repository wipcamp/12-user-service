package com.wipcamp.userservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue
	private int id;

	@JsonBackReference(value = "answerUser")
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "wip_id")
	private User user;

	@NonNull
	@ManyToOne
	@JoinColumn(name="question_id",referencedColumnName = "id")
	private Question question;

	@NotNull
	@NotBlank
	@NonNull
	@Column(nullable = true, length=9999)
	private String answerContent;

	public Answer() {
	}

	public Answer(User user, @NotNull @NotBlank Question question, @NotNull @NotBlank String answerContent) {
		this.user = user;
		this.question = question;
		this.answerContent = answerContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
}
