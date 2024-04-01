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

import com.soloProject.models.Comment;
import com.soloProject.models.Product;
import com.soloProject.models.User;
import com.soloProject.services.CategoryService;
import com.soloProject.services.CommentService;
import com.soloProject.services.ProductService;
import com.soloProject.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class NonAdminController {

	// First Step Usually add this to link this controller with services :
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CommentService commentService;

	// ? Get One Product :

	@RequestMapping("/product/{id}")
	public String Product(@PathVariable("id") Long id, Principal principal, Model model, HttpSession session) {
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

		// Access comments associated with the product
		List<Comment> comments = product.getComments();
		model.addAttribute("comments", comments);

		return "user/showOneProduct.jsp";
	}

	// ? Get Purchase List :
	@RequestMapping("/product/{id}/purchase")
	public String purchaseProduct(Principal principal, @PathVariable("id") Long id, HttpSession session, Model model) {
		if (principal == null) {
			return "redirect:/login";
		}
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		// Find the product
		Product product = productService.findProduct(id);
		if (product != null) {
			// set Product : set user to UserWhoOwnsPurchaseList
			product.setUserWhoOwnsPurchaseList(user);
			productService.updateProduct(product);
			System.out.println(product);
		}

		return "user/purchaseList.jsp";
	}

	// ? Delete Association : Product From Purchase List :// ? Delete Product From
	// Purchase List :
	@RequestMapping("/deleteProductFromPurchaseList/{id}")
	public String deleteProductFromPurchaseList(@PathVariable("id") Long id) {
		// Find the product
		Product product = productService.findProduct(id);

		if (product != null) {
			// Remove the association with the user
			product.setUserWhoOwnsPurchaseList(null);

			// Update the product
			productService.updateProduct(product);

			return "redirect:/home";
		} else {
			return "redirect:/error";
		}
	}

	// ? Create Comment :

	@RequestMapping("/product/{id}/comment")
	public String comment(@ModelAttribute("comment") Comment comment, @ModelAttribute("product") Product product,
			Principal principal, Model model, HttpSession session) {

		if (principal == null) {
			return "redirect:/login";
		}
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		System.out.println(user);

		return "user/newComment.jsp";
	}

	@PostMapping("/addComment")
	public String createComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result, Model model,
			@RequestParam("product") Long productId) {
		if (result.hasErrors()) {

			model.addAttribute("errors", result.getAllErrors());
			return "user/newComment.jsp";
		} else {
			// Find the product by its ID
			Product product = productService.findProduct(productId);
			if (product == null) {
				// Handle case when product is not found
				return "redirect:/error";
			}

			// Create the comment
			commentService.createComment(comment);

			// Redirect back to the product page
			return "redirect:/product/" + productId;
		}
	}
}
