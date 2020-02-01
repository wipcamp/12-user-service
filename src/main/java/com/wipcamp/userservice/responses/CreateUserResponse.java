package com.wipcamp.userservice.responses;

import com.wipcamp.userservice.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserResponse {
	private String message;
	private String token;
}
