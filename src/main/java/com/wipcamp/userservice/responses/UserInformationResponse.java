package com.wipcamp.userservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationResponse {
	private int total,accepted,registered,generalAnswered,majorAnswered,submitted;
}
