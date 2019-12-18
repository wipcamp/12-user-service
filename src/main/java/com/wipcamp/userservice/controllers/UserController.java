package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/user/{userId}")
	public ResponseEntity<User> register(@RequestBody @Valid User newUser){
		return new ResponseEntity<User>(userService.addUser(newUser) , HttpStatus.CREATED);
	}
}
