package com.soloProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soloProject.models.Category;
import com.soloProject.models.Product;


public interface ProductRepository extends  CrudRepository<Product, Long> {

	
	// this method retrieves all the books from the database
    //Mondatory
	List<Product> findAll();
	
	//Optional
    // this method finds books with descriptions containing the search string
    List<Product> findByNameContaining(String search);

	List<Product> findByCategoriesNotContains(Category category);
}