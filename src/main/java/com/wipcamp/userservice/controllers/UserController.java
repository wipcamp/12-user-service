package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	//Example of Mapping User
	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
		try{
			//if user already create account response with thier information and status found
			return new ResponseEntity<User>(userService.findById(userId), HttpStatus.FOUND)	;
		} catch(NoSuchElementException ex){
			//if user never visit and don't have account before, create account in db and status created
			return new ResponseEntity<User>(new User(), HttpStatus.CREATED);
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserInformation(@RequestBody @Valid User newUser ,  BindingResult theBindingResult){
		if (theBindingResult.hasErrors()){
			//if binding result has error so return bad request
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else{
			//if dont have error in code so add information to user and status created
			return new ResponseEntity<User>(userService.updateUser(newUser) , HttpStatus.CREATED);
		}
	}
}
