package com.soloProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soloProject.models.Comment;

@Repository
public interface CommentRepository extends  CrudRepository<Comment, Long> {

	
	// this method retrieves all the books from the database
    //Mondatory
	List<Comment> findAll();

}
