package com.vgs.challenge.utils;

import com.vgs.challenge.model.UserDetails;
import com.vgs.challenge.pojos.UserRequestDto;

public class TestHelper {

    public static UserDetails createUser() {
	return UserDetails.builder().username("frankokafor").birthDay("2000-12-03").build();
    }

    public static UserRequestDto userRequest() {
	return UserRequestDto.builder().username("frankokafor").birthDay("2000-12-03").build();
    }

}
