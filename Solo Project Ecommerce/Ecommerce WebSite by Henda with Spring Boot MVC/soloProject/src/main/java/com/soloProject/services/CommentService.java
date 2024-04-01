package com.soloProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soloProject.models.Comment;
import com.soloProject.repositories.CommentRepository;

@Service
public class CommentService {

	// adding the Comment repository as a dependency
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	// returns all the Comments
	public List<Comment> allComments() {
		return commentRepository.findAll();
	}

	// creates a Comment
	public Comment createComment(Comment b) {
		return commentRepository.save(b);
	}

	// retrieves a Comment
	public Comment findComment(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if (optionalComment.isPresent()) {
			return optionalComment.get();
		} else {
			return null;
		}
	}

	// update a Comment
	public Comment updateComment(Comment b) {
		return commentRepository.save(b);
	}

	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

	public Comment findById(Long id) {
		return commentRepository.findById(id).orElse(null);
	}

}