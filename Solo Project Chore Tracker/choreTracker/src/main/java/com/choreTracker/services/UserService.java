package com.choreTracker.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.choreTracker.models.LoginUser;
import com.choreTracker.models.User;
import com.choreTracker.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// 1---------- register method! -------------

	// This method will be called from the controller
	// whenever a user submits a registration form.

	public User register(User newUser, BindingResult result) {
		// Check if the mail already exist in our DB
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());

		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "An account with that email already exists!");
		}
		// Check if the password matches with confirm password
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		}
		// return null if validation fail
		if (result.hasErrors()) {
			return null;
		}
		// if validation "PASSED", Hash the password , and then save it to the DB
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}

	// 2---------- Login method! -------------

	// This method will be called from the controller
	// whenever a user submits a login form.
	public User login(LoginUser newLoginUser, BindingResult result) {
		// TO-DO - Reject values:

		Optional<User> potentialUser = userRepo.findByEmail(newLoginUser.getEmail());
		// Check if Email matches ( user exists in DB)
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "User not found!");
			return null;
		}

		User user = potentialUser.get();
		// Check if pwd typed matches with Hashed pwd in DB
		if (!BCrypt.checkpw(newLoginUser.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password!");
		}

		if (result.hasErrors()) {
			return null;
		}
		// if Typed mail and pwd matches with a user in DB, return our user
		return user;
	}

	// 3-----------Find User By id ------------------
	public User findById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		}
		return null;
	}

}