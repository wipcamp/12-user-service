package com.wipcamp.userservice.utils;

import org.springframework.http.HttpStatus;

public class FailureResponse extends ResponseForm{

	private String error;

	public FailureResponse() {
		this(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred");
	}

	public FailureResponse(String error) {
		this(HttpStatus.INTERNAL_SERVER_ERROR,error);
	}

	public FailureResponse(HttpStatus code,String error){
		super.setCode(code);
		super.setSuccess(false);
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
