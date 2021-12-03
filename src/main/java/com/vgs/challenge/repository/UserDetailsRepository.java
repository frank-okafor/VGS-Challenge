package com.vgs.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vgs.challenge.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String email);

    @Query(value = "select count(*) from user_details u where u.username = :username", nativeQuery = true)
    int checkIfUsernameExists(@Param("username") String username);

}
