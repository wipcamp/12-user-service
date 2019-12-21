package com.wipcamp.userservice.services;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.controllers.QuestionController;
import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.repositories.QuestionRepository;

import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.ResponseForm;

import com.wipcamp.userservice.utils.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;

	Logger logger = LoggerFactory.getLogger(QuestionController.class);

	public QuestionService(QuestionRepository questionRepository){
		this.questionRepository = questionRepository;
	}

	public Question getSingleQuestion(Long questionId){
		return questionRepository.findById(questionId).get();
	}

//	public List<Question> getAllQuestion(){
//		return questionRepository.findAll();
//	}

//	public List<Question> getQuestionListByMajorId(Long majorId){
//		return questionRepository.findByMajorId(majorId);
//	}
	public ResponseForm getQuestionListByMajorId(Long majorId , HttpServletRequest request){
		ResponseForm result = new FailureResponse();

		List<Question> allQuestion = this.questionRepository.findByMajorId(majorId);

		if (allQuestion.isEmpty()) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No question in this major found in database." );
			((FailureResponse) result).setError("No question in this major found in database.");
		} else{
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Question size in this major is " + allQuestion.size());
			result = new SuccessResponse<Question>(HttpStatus.OK, allQuestion);
		}
		return result;
	}

	public ResponseForm getAllQuestion(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		List<Question> allQuestion = this.questionRepository.findAll();

		if (allQuestion.isEmpty()) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No question in database" );
			((FailureResponse) result).setError("No question found in database.");
		} else{
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Question size is " + allQuestion.size());
			result = new SuccessResponse<Question>(HttpStatus.OK, allQuestion);
		}
		return result;
	}

	public ResponseForm getQuestionByQuestionId(long questionId, HttpServletRequest request) {

		ResponseForm result = new FailureResponse();

		try{
			Question currentQuestion = this.questionRepository.findById(questionId).get();
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Question is " + currentQuestion.getName());
			ArrayList<Question> questions = new ArrayList<>();
			questions.add(currentQuestion);
			result = new SuccessResponse<Question>(HttpStatus.OK, questions);
		} catch(NoSuchElementException ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Question not found");
		}
		return result;
	}
}
