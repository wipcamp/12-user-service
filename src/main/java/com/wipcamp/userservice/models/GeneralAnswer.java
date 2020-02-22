package com.wipcamp.userservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "general_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralAnswer {

	@Id
	@GeneratedValue
	private int id;

	@Lob
	@Column(nullable = true, length=9999)
	private String firstAnswer;

	@Lob
	@Column(nullable = true, length=9999)
	private String secondAnswer;

	@Lob
	@Column(nullable = true, length=9999)
	private String thirdAnswer;

	@Lob
	@Column(nullable = true, length=9999)
	private String forthAnswer;
}
