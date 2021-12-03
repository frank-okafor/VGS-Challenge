package com.vgs.challenge.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vgs.challenge.exceptions.ServiceException;
import com.vgs.challenge.model.UserDetails;
import com.vgs.challenge.pojos.ServiceResponse;
import com.vgs.challenge.pojos.UserRequestDto;
import com.vgs.challenge.repository.UserDetailsRepository;
import com.vgs.challenge.utils.ApplicationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsRepository userDetailsRepository;

    public ServiceResponse addNewUser(UserRequestDto request) {
	if (!ApplicationUtils.isUsernameValid(request.getUsername())) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "username must be letters only");
	}
	if (userDetailsRepository.checkIfUsernameExists(request.getUsername()) > 0) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST,
		    "username " + request.getUsername() + " is used by another user");
	}

	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	formater.setLenient(false);
	Date birthDate = null;
	try {
	    birthDate = formater.parse(request.getBirthDay());
	} catch (Exception e) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "birthday must be in format yyyy-MM-dd");
	}

	if (!ApplicationUtils.isBirthdayValid(birthDate)) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "Birthday cannot be after today");
	}

	userDetailsRepository
		.save(UserDetails.builder().birthDay(request.getBirthDay()).username(request.getUsername()).build());

	return ServiceResponse.builder().message("User saved successfully").status(HttpStatus.OK).build();
    }

    public ServiceResponse updateUser(UserRequestDto request) {
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	formater.setLenient(false);
	Date birthDate = null;
	try {
	    birthDate = formater.parse(request.getBirthDay());
	} catch (Exception e) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "birthday must be in format yyyy-MM-dd");
	}

	if (!ApplicationUtils.isBirthdayValid(birthDate)) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "Birthday cannot be after today");
	}
	return userDetailsRepository.findByUsername(request.getUsername()).map(user -> {
	    user.setBirthDay(request.getBirthDay());
	    userDetailsRepository.save(user);
	    return ServiceResponse.builder().message("user updated successfully").build();
	}).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND,
		"user with email " + request.getUsername() + " does not exist"));
    }

    public ServiceResponse getBirthdayMessage(String username) {
	return userDetailsRepository.findByUsername(username).map(user -> {
	    String message = "";
	    long days = ApplicationUtils.getDaysToBirthday(user.getBirthDay());
	    if (days == 0)
		message = "Hello," + username + "! Happy birthday!";
	    else if (days > 0)
		message = "Hello, " + username + "! Your birthday is in " + days + " day(s)";
	    else
		message = "Hello, " + username + "! Sorry your birthday has passed";
	    return ServiceResponse.builder().message(message).build();
	}).orElseThrow(
		() -> new ServiceException(HttpStatus.NOT_FOUND, "user with email " + username + " does not exist"));

    }

}
