package com.wipcamp.userservice.models;

import lombok.Data;

import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schools")
@Data
@NoArgsConstructor
public class School {

	@Id
	@GeneratedValue
	private int id;

	@NonNull
	private String name;

	@NonNull
	private String level;

	@NonNull
	private String major;

	@NonNull
	private String gpax;


}
