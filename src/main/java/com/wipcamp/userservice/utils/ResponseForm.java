package com.wipcamp.userservice.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ResponseForm {

	@NotNull
	private HttpStatus code;

	@NonNull
	private boolean success;

	@JsonIgnore
	public HttpStatus getHttpCode() {
		return code;
	}

	public int getCode() {
		return code.value();
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
