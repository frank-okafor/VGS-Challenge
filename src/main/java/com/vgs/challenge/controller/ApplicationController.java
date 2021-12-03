package com.vgs.challenge.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgs.challenge.pojos.ServiceResponse;
import com.vgs.challenge.pojos.UserRequestDto;
import com.vgs.challenge.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ApplicationController {
    private final UserService service;

    @PostMapping
    @ApiOperation(value = "create a new user")
    public ResponseEntity<ServiceResponse> addNewUser(@Valid @RequestBody UserRequestDto request) {
	ServiceResponse response = service.addNewUser(request);
	return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping
    @ApiOperation(value = "update existing user details")
    public ResponseEntity<ServiceResponse> updateUser(@Valid @RequestBody UserRequestDto request) {
	ServiceResponse response = service.updateUser(request);
	return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/getuserbirthday/{username}")
    @ApiOperation(value = "get customer by email address")
    public ResponseEntity<ServiceResponse> getBirthdayMessage(@PathVariable("username") String username) {
	ServiceResponse response = service.getBirthdayMessage(username);
	return new ResponseEntity<>(response, response.getStatus());
    }

}
