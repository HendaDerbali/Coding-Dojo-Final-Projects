package com.soloProject.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soloProject.models.Category;
import com.soloProject.models.Product;
import com.soloProject.models.User;
import com.soloProject.services.CategoryService;
import com.soloProject.services.ProductService;
import com.soloProject.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {

	// First Step Usually add this to link this controller with services :
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	// Routes
	@RequestMapping("/home1")
	public String adminHome(Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/login";
		}
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		return "admin/home1.jsp";
	}

	@RequestMapping("/categories-products/new")
	public String newCategoryAnDProduct(@ModelAttribute("category") Category category,
			@ModelAttribute("product") Product product, Principal principal, Model model, HttpSession session) {

		if (principal == null) {
			return "redirect:/login";
		}
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		// List Products and Categories to display in Association lists
		List<Category> categories = categoryService.allCategorys(); // Make sure this method name matches your service
																	// method
		List<Product> products = productService.allProducts(); // Same here, ensure method name is correct
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);

		return "admin/new.jsp";
	}

	// ? Create Product:

	@PostMapping("/addProduct")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Ensure the product attribute and Categories present
			model.addAttribute("product", product);
			model.addAttribute("category", new Category());

			model.addAttribute("errors", result.getAllErrors());
			return "admin/new.jsp";
		} else {
			productService.createProduct(product);
			return "redirect:/home1";
		}
	}

	// ? Create Category:

	@PostMapping("/addCategory")
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			// Ensure the product attribute and Categories present
			model.addAttribute("category", category);
			model.addAttribute("product", new Product());

			model.addAttribute("errors", result.getAllErrors());
			return "admin/new.jsp";
		} else {

			categoryService.createCategory(category);
			return "redirect:/home1";
		}
	}

	// Associate Product To Category :

	@PostMapping("/associateCategoriesProducts")
	public String addCategoryToProduct(@RequestParam("categoryId") Long categoryId,
	        @RequestParam("productId") Long productId, Model model, Principal principal) {

	    // Retrieve the authenticated user
	    String email = principal.getName();
	    User user = userService.findByEmail(email);

	    // Find the product and category
	    Product product = productService.findProduct(productId);
	    Category category = categoryService.findCategory(categoryId);

	    // Ensure that the product and category exist and are associated with the current user
	    if (product == null || category == null || !user.equals(product.getUser())
	            || !user.equals(category.getUser())) {
	        return "redirect:/home1"; // Redirect if product or category is not found or not associated with the user
	    }

	    // Check if the category is already associated with the product
	    if (!product.getCategories().contains(category)) {
	        // Add the category to the product and vice versa
	        product.getCategories().add(category);
	        category.getProducts().add(product);

	        // Save changes to the database
	        productService.updateProduct(product);
	        categoryService.updateCategory(category);
	    }

	    return "redirect:/home1"; // Redirect to the admin home page
	}


}
