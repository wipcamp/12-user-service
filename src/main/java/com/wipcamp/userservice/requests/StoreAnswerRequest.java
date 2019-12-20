package com.wipcamp.userservice.requests;

import com.wipcamp.userservice.models.Answer;
import com.wipcamp.userservice.requests.models.AnswerRequest;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import java.util.List;

public class StoreAnswerRequest {

	@NotNull(message = "Required answers field")
	@NotEmpty(message = "Request answers field cannot	 be empty or null")
	@Size(min = 1,message = "Request answers must have at least 1 answer")
	private List<AnswerRequest> answers;

	public List<AnswerRequest> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerRequest> answers) {
		this.answers = answers;
	}
}
