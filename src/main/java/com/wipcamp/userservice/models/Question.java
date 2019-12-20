package com.wipcamp.userservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue
	private int id;

	@NonNull
	private String name;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;


	@OneToMany(mappedBy = "question")
	private List<Answer> answerList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NonNull
	public String getName() {
		return name;
	}

	public void setName(@NonNull String name) {
		this.name = name;
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
