package com.vgs.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vgs.challenge.exceptions.ServiceException;
import com.vgs.challenge.model.UserDetails;
import com.vgs.challenge.pojos.ServiceResponse;
import com.vgs.challenge.pojos.UserRequestDto;
import com.vgs.challenge.repository.UserDetailsRepository;
import com.vgs.challenge.utils.TestHelper;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {
	@Mock
	private UserDetailsRepository userDetailsRepository;

	private UserService service;

	@BeforeEach
	void setUp() throws Exception {
		service = new UserService(userDetailsRepository);
	}

	@Test
	void testAddNewUser() {
		UserDetails user = TestHelper.createUser();
		when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(user);
		ServiceResponse response = service.addNewUser(TestHelper.userRequest());
		assertThat(response.getMessage()).isEqualTo("User saved successfully");
	}

	@Test
	void testGetBirthdayMessage() {
		UserDetails user = TestHelper.createUser();
		when(userDetailsRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		String username = user.getUsername();
		ServiceResponse response = service.getBirthdayMessage(username);
		assertThat(response.getMessage()).isEqualTo("Hello, " + username + "! Your birthday is in " + 2 + " day(s)");
	}

	@Test
	void testUsernameExistError() {
		UserDetails user = TestHelper.createUser();
		UserRequestDto dto = TestHelper.userRequest();
		when(userDetailsRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		assertThatThrownBy(() -> service.addNewUser(dto)).isInstanceOf(ServiceException.class)
				.hasMessageContaining("username " + dto.getUsername() + " is used by another user");
		verify(userDetailsRepository, never()).save(any());
	}

}
