package com.vgs.challenge.pojos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
	@NotNull(message = "username cannot be null")
	@NotBlank(message = "username cannot be empty")
	private String username;
	@NotNull(message = "birthDay cannot be null")
	@NotBlank(message = "birthDay cannot be empty")
	@Pattern(regexp = "[a-zA-Z]+", message = "username must be letters only")
	private LocalDate birthDay;
}
