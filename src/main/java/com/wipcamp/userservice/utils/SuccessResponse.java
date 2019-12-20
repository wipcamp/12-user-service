package com.wipcamp.userservice.utils;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class SuccessResponse<T> extends ResponseForm {

	private List<T> data;

	public SuccessResponse() {
		this(HttpStatus.OK,new ArrayList<>());
	}

	public SuccessResponse(List<T> data) {
		this(HttpStatus.OK,data);
	}

	public SuccessResponse(HttpStatus code,List<T> data) {
		super.setCode(code);
		super.setSuccess(true);
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}


}
