package com.wipcamp.userservice.models;

public class Parent {
	private long telNo;
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
