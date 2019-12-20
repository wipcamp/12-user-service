package com.wipcamp.userservice.services;

import com.wipcamp.userservice.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;
}
