package com.soloProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloProject.models.User;
import com.soloProject.services.UserService;
import com.soloProject.validator.UserValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApiController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserApiController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/register")
    public void registration(@Valid @ModelAttribute("user") User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            // Handle validation errors
            // For simplicity, returning null in case of validation error
            return;
        }
        // Make first user SUPER ADMIN
        if (userService.allUsers().isEmpty()) {
            userService.newUser(user, "ROLE_SUPER_ADMIN");
        } else {
            userService.newUser(user, "ROLE_USER");
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }

}
