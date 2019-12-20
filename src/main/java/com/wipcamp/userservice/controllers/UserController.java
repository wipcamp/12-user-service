package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	//Example of Mapping User
	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<User> getUser(@PathVariable("userId") long userId , HttpServletRequest request) {
		try{
			User currentUser = userService.findById(userId);
			//if user already create account response with their information and status found --> Check account which line_id or facebook_id is match this user so return information of them.
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User is " + currentUser.getFirstNameEn() + currentUser.getLastNameEn());
			return new ResponseEntity<User>(currentUser, HttpStatus.FOUND)	;
		} catch(NoSuchElementException ex){
			//if user never visit and don't have account send not found status
			logger.warn(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "user not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> updateUserInformation(
			@PathVariable("userId") long userId,
			@Valid @RequestBody User user,
			BindingResult theBindingResult,
			ModelMap model){

		if (theBindingResult.hasErrors()){
			//if binding result has error so return bad request
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else{
			//if dont have error in code so add information to user and status created
			Optional<User> userOptional = userService.findByOptionalId(userId);

			if(!userOptional.isPresent()){
				return ResponseEntity.notFound().build();
			}


			return new ResponseEntity<User>(userService.updateUser(user) , HttpStatus.CREATED);
		}
	}
}
