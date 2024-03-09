package com.choreTracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.choreTracker.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	// For Login and Registration ; To check mail is unic
	Optional<User> findByEmail(String email);

	// this method retrieves all the books from the database
	// Mondatory
	List<User> findAll();

}