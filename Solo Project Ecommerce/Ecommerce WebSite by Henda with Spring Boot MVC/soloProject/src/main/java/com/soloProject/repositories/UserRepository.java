package com.soloProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soloProject.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();

	User findByEmail(String email);
	
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.categories c LEFT JOIN FETCH u.products p WHERE u.email = :email")
    User findByEmailWithCategoriesAndProducts(@Param("email") String email);
}