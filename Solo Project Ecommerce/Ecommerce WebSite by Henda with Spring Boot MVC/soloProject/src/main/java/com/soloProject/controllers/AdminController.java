package com.soloProject.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soloProject.models.Category;
import com.soloProject.models.Product;
import com.soloProject.models.User;
import com.soloProject.services.CategoryService;
import com.soloProject.services.ProductService;
import com.soloProject.services.UserService;

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

	// Route : To add new Category & Products ( 2 forms in the same jsp file)
	@RequestMapping("/categories-products/new")
	public String newCategorieAndProduct(Model model, Principal principal) {
	    // Retrieve the email of the logged-in user
	    String email = principal.getName();
	    // Retrieve the user based on the email along with their categories and products
	    User user = userService.findByEmailWithCategoriesAndProducts(email);

	    // Retrieve the categories and products associated with the current user
	    List<Category> userCategories = user.getCategories();
	    List<Product> userProducts = user.getProducts();

	    // Pass the user's categories and products to the view
	    model.addAttribute("categories", userCategories);
	    model.addAttribute("products", userProducts);

	    // Add empty Category and Product to the model for form submission
	    model.addAttribute("category", new Category());
	    model.addAttribute("product", new Product());

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

	@GetMapping("/associationForm")
	public String showAssociationForm(Model model, Principal principal) {
		// Retrieve the email of the logged-in user
		String email = principal.getName();
		// Retrieve the user based on the email
		User user = userService.findByEmail(email);

		// Retrieve only the products and categories associated with the current user
		List<Product> userProducts = user.getProducts();
		List<Category> userCategories = user.getCategories();

		// Pass the user's products and categories to the view
		model.addAttribute("products", userProducts);
		model.addAttribute("categories", userCategories);
		model.addAttribute("user", user);

		return "association-form";
	}

	@PostMapping("/associateCategoriesProducts")
	public String associateCategoriesProducts(@RequestParam("categoryId") Long categoryId,
			@RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
		// Retrieve the user
		User user = userService.findById(userId);

		// Retrieve the category and product
		Category category = categoryService.findById(categoryId);
		Product product = productService.findById(productId);

		// Associate the category with the product
		category.getProducts().add(product);
		product.getCategories().add(category);

		// Save the changes
		categoryService.updateCategory(category);
		productService.updateProduct(product);

		return "redirect:/home1"; // Redirect to the admin home page
	}

}
