package com.wipcamp.userservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {
	@Getter
	@Setter
	private int yesterday,today,count;
}
