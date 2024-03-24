package com.soloProject.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soloProject.models.Product;
import com.soloProject.models.User;
import com.soloProject.services.CategoryService;
import com.soloProject.services.ProductService;
import com.soloProject.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NonAdminController {
	
	// First Step Usually add this to link this controller with services :
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	// ? Get One Product :

		@RequestMapping("/product/{id}")
		public String editProduct(@PathVariable("id") Long id, Principal principal, Model model, HttpSession session) {
			// session
			if (principal == null) {
				return "redirect:/login";
			}

			String email = principal.getName();
			User user = userService.findByEmail(email);
			model.addAttribute("user", user);
			System.out.println(user);

			Product product = productService.findProduct(id);
			model.addAttribute("product", product);
			System.out.println(product);

			return "user/showOneProduct.jsp";
		}

}
