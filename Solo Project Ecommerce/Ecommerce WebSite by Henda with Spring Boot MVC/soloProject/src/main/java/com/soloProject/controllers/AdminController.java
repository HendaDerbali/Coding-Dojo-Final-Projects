package com.soloProject.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/")
public class AdminController {

	// First Step Usually add this to link this controller with services :
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	// Dashboard Route  :
	@GetMapping("")
	public String test(@ModelAttribute("category") Category category, Model model) {
		// List Products and Categories to display in Association lists
		List<Category> categories = categoryService.allCategorys();
		model.addAttribute("categories", categories);
		return "dashboard.jsp";
	}

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

		// List Products and Categories to display in Association lists
		List<Category> categories = categoryService.allCategorys(); // method
		List<Product> products = productService.allProducts();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);

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
			@RequestParam("productId") Long productId) {

		// Find the product and category based on id s
		Product product = productService.findProduct(productId);
		Category category = categoryService.findCategory(categoryId);

		// Check if both product and category exist
		if (product != null && category != null) {
			// Check if the category is not already associated with the product
			if (!product.getCategories().contains(category)) {
				// Add the category to the product and vice versa
				product.getCategories().add(category);
				productService.updateProduct(product);
			}
		}

		// Redirect to the home page
		return "redirect:/home1";
	}

	// Edit Category :
	// ? Edit :

	@RequestMapping("/categories/{id}")
	public String editCategory(@PathVariable("id") Long id, Principal principal, Model model, HttpSession session) {

		if (principal == null) {
			return "redirect:/login";
		}

		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		Category category = categoryService.findCategory(id);
		model.addAttribute("category", category);
		System.out.println(category);

		return "admin/editCategory.jsp";
	}

	@RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.PUT)
	public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
			Principal principal, Model model, HttpSession session, @PathVariable("categoryId") Long categoryId) {
		// Session
		if (principal == null) {
			return "redirect:/login";
		}

		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/editCategory.jsp";
		} else {
			// Set the category id before updating
			category.setId(categoryId);
			categoryService.updateCategory(category);
			return "redirect:/home1";
		}
	}

	// Edit Product :
	// ? Edit :

	@RequestMapping("/products/{id}")
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

		return "admin/editProduct.jsp";
	}

	@RequestMapping(value = "/editProduct/{productId}", method = RequestMethod.PUT)
	public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
			Principal principal, Model model, HttpSession session, @PathVariable("productId") Long productId) {
		// Session
		if (principal == null) {
			return "redirect:/login";
		}

		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/editProduct.jsp";
		} else {
			// Set the Product id before updating
			product.setId(productId);
			productService.updateProduct(product);
			return "redirect:/home1";
		}
	}

	// ? Delete Product :
	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
	public String destroyProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/home1";
	}

	// ? Delete Category :
	@RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.DELETE)
	public String destroyCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/home1";
	}

}
