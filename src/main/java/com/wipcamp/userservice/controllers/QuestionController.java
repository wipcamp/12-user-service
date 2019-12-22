package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.services.QuestionService;

import com.wipcamp.userservice.utils.ResponseForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@CrossOrigin("${CROSSSITEDOMAIN}")
@RestController
public class QuestionController {
	@Autowired
	QuestionService questionService;

	@GetMapping("/questions")
	public ResponseEntity<ResponseForm> getAllQuestion(HttpServletRequest request){
		ResponseForm result = questionService.getAllQuestion(request);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@GetMapping("/question/{questionId}")
	public ResponseEntity<ResponseForm> getQuestionByQuestionId(@PathVariable("questionId") long questionId , HttpServletRequest request){
		ResponseForm result = questionService.getQuestionByQuestionId(questionId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

}
