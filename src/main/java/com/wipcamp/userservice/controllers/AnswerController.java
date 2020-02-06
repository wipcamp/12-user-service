package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.requests.StoreAnswerRequest;
import com.wipcamp.userservice.services.AnswerService;

import com.wipcamp.userservice.utils.ResponseForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("${CROSSSITEDOMAIN}")
@RestController
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@PostMapping("/answer")
	public ResponseEntity<ResponseForm> createAnswer(@RequestBody @Valid StoreAnswerRequest request, @RequestParam long userId,
			@RequestParam long majorId){
		ResponseForm result = this.answerService.createAnswer(request,userId,majorId);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@PostMapping("/me/answer")
	public ResponseEntity<ResponseForm> createAnswer(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid StoreAnswerRequest request,
			@RequestParam long majorId){
		ResponseForm result = this.answerService.createAnswerByToken(request,token,majorId);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}
}
