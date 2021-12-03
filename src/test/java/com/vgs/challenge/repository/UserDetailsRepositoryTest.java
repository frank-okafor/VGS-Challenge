package com.vgs.challenge.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.vgs.challenge.model.UserDetails;
import com.vgs.challenge.utils.TestHelper;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(Lifecycle.PER_CLASS)
class UserDetailsRepositoryTest {
    private @Autowired UserDetailsRepository userDetailsRepository;

    @BeforeEach
    void setUp() throws Exception {
	userDetailsRepository.deleteAll();
    }

    @Test
    void testFindByUsername() {
	UserDetails user = TestHelper.createUser();
	userDetailsRepository.save(user);
	assertThat(userDetailsRepository.findByUsername(user.getUsername()).isPresent()).isTrue();
    }

    @Test
    void testCheckIfUsernameExists() {
	UserDetails user = TestHelper.createUser();
	userDetailsRepository.save(user);
	assertThat(userDetailsRepository.checkIfUsernameExists(user.getUsername())).isEqualTo(1);
    }

}
