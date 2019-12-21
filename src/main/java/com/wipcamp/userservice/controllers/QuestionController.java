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
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionService questionService;

	Logger logger = LoggerFactory.getLogger(QuestionController.class);

//	@GetMapping("/all")
//	public ResponseEntity<List<Question>> getAllQuestion(HttpServletRequest request){
//		List<Question> allQuestion = questionService.getAllQuestion();
//		if(allQuestion.isEmpty()) {
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No question in database" );
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} else{
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Question size is " + allQuestion.size());
//			return new ResponseEntity<List<Question>>(questionService.getAllQuestion(),HttpStatus.FOUND);
//		}
//	}

	@GetMapping("")
	public ResponseEntity<ResponseForm> getAllQuestion(HttpServletRequest request){
		ResponseForm result = questionService.getAllQuestion(request);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}


//	@GetMapping("/{questionId}")
//	public ResponseEntity<Question> getQuestionByQuestionId(@PathVariable("questionId") long questionId , HttpServletRequest request){
//		return new ResponseEntity<Question>(questionService.getSingleQuestion(questionId),HttpStatus.FOUND);
//	}

	@GetMapping("/{questionId}")
	public ResponseEntity<ResponseForm> getQuestionByQuestionId(@PathVariable("questionId") long questionId , HttpServletRequest request){
		ResponseForm result = questionService.getQuestionByQuestionId(questionId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/major/{majorId}")
	public ResponseEntity<ResponseForm> getAllQuestionByMajorid(@PathVariable("majorId") long majorId , HttpServletRequest request){
		ResponseForm result = questionService.getQuestionListByMajorId(majorId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}
}
