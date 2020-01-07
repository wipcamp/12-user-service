package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.services.UserService;

import com.wipcamp.userservice.utils.ResponseForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@CrossOrigin("${CROSSSITEDOMAIN}")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("")
	public ResponseEntity<ResponseForm> createUser(HttpServletRequest request) {
		ResponseForm result = userService.createUser(request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseForm> getUserByUserId(@PathVariable("userId") long userId, HttpServletRequest request) {
		ResponseForm result = userService.getUserByUserId(userId, request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ResponseForm> updateUserInformation(@PathVariable("userId") long userId, @Valid @RequestBody User user,
			BindingResult theBindingResult, ModelMap model) {
		ResponseForm result = userService.updateUser(user, userId);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@GetMapping("/line/{lineId}")
	public ResponseEntity<ResponseForm> getUserByLineId(@PathVariable("lineId") long lineId , HttpServletRequest request){
		ResponseForm result = userService.getUserByLineId(lineId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/me")
	public ResponseEntity<ResponseForm> getUserByToken(@RequestHeader(name = "Authorization", required = true) String token , HttpServletRequest request){
		ResponseForm result = userService.getUserByToken(token);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@PutMapping("/me")
	public ResponseEntity<ResponseForm> updateUserByToken(@RequestBody User user){
		ResponseForm result = userService.updateUserByToken(user);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}
}

