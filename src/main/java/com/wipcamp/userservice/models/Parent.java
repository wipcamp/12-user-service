package com.wipcamp.userservice.models;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parents")
public class Parent {

	@Id
	@GeneratedValue
	private int id;

	@NonNull
	private long telNo;

	@NonNull
	private String relation;

	public Parent(long telNo, String relation) {
		this.telNo = telNo;
		this.relation = relation;
	}

	public long getTelNo() {
		return telNo;
	}

	public void setTelNo(long telNo) {
		this.telNo = telNo;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
}
