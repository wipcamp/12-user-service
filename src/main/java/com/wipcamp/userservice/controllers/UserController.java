package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	//Example of Mapping User
	@GetMapping("/user/{userId}")
	@ResponseBody
	public User getUser(@PathVariable int userId) {
		try{
			return userService.findById(userId)	;
		} catch(Exception ex){
			return new User();
		}
	}

	@PostMapping("/user/{userId}")
	public ResponseEntity<User> register(@RequestBody @Valid User newUser){
		return new ResponseEntity<User>(userService.addUser(newUser) , HttpStatus.CREATED);
	}
}
