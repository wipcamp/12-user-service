package com.wipcamp.userservice.services;

import com.wipcamp.userservice.models.Answer;
import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.AnswerRepository;
import com.wipcamp.userservice.repositories.MajorRepository;
import com.wipcamp.userservice.repositories.UserRepository;
import com.wipcamp.userservice.requests.StoreAnswerRequest;

import com.wipcamp.userservice.requests.models.AnswerRequest;
import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.ResponseForm;

import com.wipcamp.userservice.utils.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MajorRepository majorRepository;

	public ResponseForm createAnswer(StoreAnswerRequest request, long userId, long majorId) {

		ResponseForm result = new FailureResponse();

		User userFromPath = this.userRepository.findById(userId).orElse(null);
		// User in session later implement
		User userFromSession;

		if (null == userFromPath /* || userFromSession == null */) {
			((FailureResponse) result).setError("User not found");
		}
		//		else if(userFromPath != userFromSession){
		//
		//		}
		else {
			if (!userFromPath.getAnswerList().isEmpty()) {
				((FailureResponse) result).setError("This user has answered this the question already");
			} else {

				Major major = this.majorRepository.findById(majorId).orElse(null);

				if (null == major) {
					((FailureResponse) result).setError("Major not found");
				} else {
					ArrayList<Integer> questionIdFromMajor = new ArrayList<>();
					major.getQuestionList().forEach((question) -> questionIdFromMajor.add(question.getId()));

					ArrayList<Integer> questionIdFromRequest = new ArrayList<>();
					request.getAnswers().forEach((requestAnswer) -> questionIdFromRequest.add(requestAnswer.getQuestion_id()));

					if (questionIdFromMajor.equals(questionIdFromRequest)) {
						List<Question> questionList = major.getQuestionList();

						ArrayList<Answer> resultData = new ArrayList<>();

						for (int i = 0; i < questionList.size(); i++) {
							Question question = questionList.get(i);
							for (AnswerRequest answerRequest : request.getAnswers()) {
								if (question.getId() == answerRequest.getQuestion_id()) {
									Answer answer = new Answer(userFromPath, question, answerRequest.getAnswer_content());
									answerRepository.save(answer);
									resultData.add(answer);
								}
							}
						}
						result = new SuccessResponse<Answer>(HttpStatus.OK, resultData);
					} else {
						((FailureResponse) result).setError("Question Id from request and major does not match");
					}
				}
			}
		}
		return result;
	}
}
