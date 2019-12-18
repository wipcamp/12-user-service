package com.wipcamp.userservice.models;

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

	@ManyToOne
	@JoinColumn(name="major_id",referencedColumnName = "id")
	private Major major;

	@OneToMany(mappedBy = "question")
	private List<Answer> answerList;
}
