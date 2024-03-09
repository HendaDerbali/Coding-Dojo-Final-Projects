package com.soloProject.controllers;

import java.security.Principal;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soloProject.models.User;
import com.soloProject.services.UserService;
import com.soloProject.validator.UserValidator;

//HELP
// https://stackoverflow.com/questions/74907533/the-method-antmatchersstring-is-undefined-for-the-type
// https://stackoverflow.com/questions/74683225/updating-to-spring-security-6-0-replacing-removed-and-deprecated-functionality
// https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html

@Controller
public class UserController {

	private UserService userService;
	private UserValidator userValidator;

	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@RequestMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			HttpSession session, HttpServletRequest request) {
		userValidator.validate(user, result);
		// Store the password before it is encrypted
		String password = user.getPassword();
		if (result.hasErrors()) {
			return "loginPage.jsp";
		}
		// Make first user SUPER ADMIN
		if (userService.allUsers().size() == 0) {
			userService.newUser(user, "ROLE_SUPER_ADMIN");
		} else {
			userService.newUser(user, "ROLE_USER");
		}

		// Log in new user with the password we stored before encrypting it
		authWithHttpServletRequest(request, user.getEmail(), password);
		return "redirect:/";
	}

	// We will call this method to automatically log in newly registered users
	public void authWithHttpServletRequest(HttpServletRequest request, String email, String password) {
		try {
			request.login(email, password);
		} catch (ServletException e) {
			System.out.println("Error while login: " + e);
		}
	}

	@RequestMapping("/admin/{id}")
	public String makeAdmin(Principal principal, @PathVariable("id") Long id, Model model) {
		if (principal == null) {
			return "redirect:/login";
		}

		User user = userService.findById(id);
		userService.upgradeUser(user);

		model.addAttribute("users", userService.allUsers());

		return "redirect:/home";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("user") User user,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		if (error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}

		return "loginPage.jsp";
	}

	@RequestMapping(value = { "/", "/home" })
	public String home(Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/login";
		}
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);

		if (user != null) {
			user.setLastLogin(new Date());
			userService.updateUser(user);
			// If the user is an ADMIN or SUPER_ADMIN they will be redirected to the admin
			// page
			if (user.getRoles().get(0).getName().contains("ROLE_SUPER_ADMIN")
					|| user.getRoles().get(0).getName().contains("ROLE_ADMIN")) {
				model.addAttribute("currentUser", userService.findByEmail(email));
				model.addAttribute("users", userService.allUsers());
				return "adminPage.jsp";
			}
			// All other users are redirected to the home page
		}

		return "home.jsp";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(Principal principal, @PathVariable("id") Long id, HttpSession session, Model model) {
		if (principal == null) {
			return "redirect:/login";
		}
		User user = userService.findById(id);
		userService.deleteUser(user);

		model.addAttribute("users", userService.allUsers());

		return "redirect:/home";
	}

}