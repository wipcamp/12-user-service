package com.wipcamp.userservice.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "majors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String name;

	private String description;

	@JsonIgnore
	@JsonManagedReference(value = "majorQuestions")
	@OneToMany(mappedBy = "major")
	private List<Question> questionList;

	@JsonIgnore
	@OneToMany(mappedBy = "major")
	private List<User> userList;

	public Major (long id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
