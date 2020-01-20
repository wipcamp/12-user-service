package com.wipcamp.userservice.reponses;

import lombok.Data;

import java.util.Date;

public class GraphDailyResponse {
	private Date startDate;
	private int total;
	private int[] dayOfWeek = new int[7];

	public GraphDailyResponse(Date startDate, int total, int[] dayOfWeek) {
		this.startDate = startDate;
		this.total = total;
		this.dayOfWeek = dayOfWeek;
	}

	public void setDayOfWeek(int[] dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSunday() {
		return dayOfWeek[0];
	}

	public int getMonday() {
		return dayOfWeek[1];
	}

	public int getTuesday() {
		return dayOfWeek[2];
	}

	public int getWednesday() {
		return dayOfWeek[3];
	}

	public int getThursday() {
		return dayOfWeek[4];
	}

	public int getFriday() {
		return dayOfWeek[5];
	}

	public int getSaturday() {
		return dayOfWeek[6];
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getTotal() {
		return total;
	}
}
