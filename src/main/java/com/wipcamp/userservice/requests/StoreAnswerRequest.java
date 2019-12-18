package com.wipcamp.userservice.requests;

import com.wipcamp.userservice.models.Answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

public class StoreAnswerRequest {
	@NotNull
	@NotBlank(message = "Request must contain array of UserAnswer")
	private List<Answer> userAnswer;
}
