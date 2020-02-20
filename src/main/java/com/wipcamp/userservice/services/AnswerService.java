package com.wipcamp.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
import com.wipcamp.userservice.utils.JwtUtility;
import com.wipcamp.userservice.utils.LoggerUtility;
import com.wipcamp.userservice.utils.ResponseForm;
import com.wipcamp.userservice.utils.SuccessResponse;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MajorRepository majorRepository;

	@Autowired
	private JwtUtility jwtUtility;

	private Logger logger = LoggerFactory.getLogger(AnswerService.class);

	public ResponseForm createAnswer(StoreAnswerRequest request, long userId, long majorId) {

		User userFromPath = this.userRepository.findById(userId).orElse(null);

		return doCreateAnswer(request, majorId, userFromPath);
	}

	public ResponseForm createAnswerByToken(StoreAnswerRequest request, String token, long majorId) {
		ResponseForm result = new FailureResponse();
		Integer wipId = null;
		try {
			wipId = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			LoggerUtility.logUserError(logger, "JWT doesn't have wipId Field token=" + token, "createAnswerByToken", token, e);
			((FailureResponse) result).setError("Cannot get wipId from Jwt Token");
		}
		User queryUser = userRepository.findById(Long.valueOf(wipId)).orElse(null);
		result = doCreateAnswer(request, majorId, queryUser);
		LoggerUtility.logUserSuccessInfo(logger, "Successful create answer by token wipId=" + wipId, "createAnswerByToken", wipId);
		return result;
	}

	private ResponseForm doCreateAnswer(StoreAnswerRequest request, long majorId, User user) {
		ResponseForm result = new FailureResponse();
		if (null == user) {
			LoggerUtility.logError(logger, "Cannot create answer for user. user not found", "doCreateAnswer");
			((FailureResponse) result).setError("User not found");
		} else {

			Major major = this.majorRepository.findById(majorId).orElse(null);

			if (null == major) {
				LoggerUtility.logFailWarning(logger,
						"Cannot create answer for user. major not found. majorId=" + majorId + " wipId=" + user.getWipId(), "doCreateAnswer",
						user.getWipId());
				((FailureResponse) result).setError("Major not found");
			} else {
				user.setMajor(major);

				ArrayList<Integer> questionIdFromMajor = new ArrayList<>();
				major.getQuestionList().forEach((question) -> questionIdFromMajor.add(question.getId()));

				ArrayList<Integer> questionIdFromRequest = new ArrayList<>();
				request.getAnswers().forEach((requestAnswer) -> questionIdFromRequest.add(requestAnswer.getQuestion_id()));

				Collections.sort(questionIdFromMajor);
				Collections.sort(questionIdFromRequest);

				if (questionIdFromMajor.equals(questionIdFromRequest)) {

					if (!user.getAnswerList().isEmpty()) {
						for (Answer answer : user.getAnswerList()) {
							answerRepository.delete(answer);
						}
					}

					List<Question> questionList = major.getQuestionList();

					ArrayList<Answer> resultData = new ArrayList<>();

					for (int i = 0; i < questionList.size(); i++) {
						Question question = questionList.get(i);
						for (AnswerRequest answerRequest : request.getAnswers()) {
							if (question.getId() == answerRequest.getQuestion_id()) {
								Answer answer = new Answer(user, question, answerRequest.getAnswer_content());
								answerRepository.save(answer);
								resultData.add(answer);
							}
						}
					}
					user.setAnswerList(resultData);
					userRepository.save(user);

					LoggerUtility.logUserSuccessInfo(logger, "Successful create answer for user wipId=" + user.getWipId(),
							"doCreateAnswer",
							user.getWipId());
					result = new SuccessResponse<Answer>(HttpStatus.OK, resultData);
				} else {
					LoggerUtility.logFailWarning(logger,
							"Cannot create answer. Question Id from request and major does not match. majorId=" + majorId + " wipId="
									+ user.getWipId(), "doCreateAnswer", user.getWipId());
					((FailureResponse) result).setError("Question Id from request and major does not match");
				}
			}

		}
		return result;
	}

}
