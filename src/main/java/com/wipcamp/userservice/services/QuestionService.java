package com.wipcamp.userservice.services;

import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;

	public QuestionService(QuestionRepository questionRepository){
		this.questionRepository = questionRepository;
	}

	public Question getSingleQuestion(Long questionId){
		return questionRepository.findById(questionId).get();
	}

	public List<Question> getAllQuestion(){
		return questionRepository.findAll();
	}

	public List<Question> getQuestionListByMajorId(Long majorId){
		return questionRepository.findAllQuestionByMajorId(majorId);
	}
}
