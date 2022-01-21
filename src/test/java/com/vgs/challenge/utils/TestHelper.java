package com.vgs.challenge.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vgs.challenge.model.UserDetails;
import com.vgs.challenge.pojos.UserRequestDto;

public class TestHelper {

	public static UserDetails createUser() {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return UserDetails.builder().username("frankokafor").birthDay(LocalDate.parse("2000-12-03", formater)).build();
	}

	public static UserRequestDto userRequest() {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return UserRequestDto.builder().username("frankokafor").birthDay(LocalDate.parse("2000-12-03", formater))
				.build();
	}

}
