package com.soloProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soloProject.models.Category;
import com.soloProject.models.Product;
import com.soloProject.repositories.ProductRepository;

@Service
public class ProductService {

	// adding the Product repository as a dependency
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// returns all the Products
	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	// List Of Products that not associated to category
	public List<Product> getUnassignedProducts(Category category) {
		return productRepository.findByCategoriesNotContains(category);
	}

	// creates a Product
	public Product createProduct(Product b) {
		return productRepository.save(b);
	}

	// retrieves a Product
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) { // isPresent() : method to check if our object exists.
			return optionalProduct.get();
		} else {
			return null;
		}
	}

	// update a Product
	public Product updateProduct(Product b) {
		return productRepository.save(b);
	}

	// find by containing
	public List<Product> ProductsContaining(String search) {
		return productRepository.findByNameContaining(search);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

}