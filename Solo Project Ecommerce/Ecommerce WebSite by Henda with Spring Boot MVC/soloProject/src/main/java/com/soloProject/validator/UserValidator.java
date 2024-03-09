package com.soloProject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soloProject.models.User;
import com.soloProject.repositories.UserRepository;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		// Check if mail is duplicated :
		if (userRepository.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Duplicate.user.email", "Email already exists");
		}
		// Check if the password and confirm password fields match
		if (!user.getConfirm().equals(user.getPassword())) {
			errors.rejectValue("confirm", "Match");
		}
	}

}