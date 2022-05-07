package com.michelabs.springbootcomongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelabs.springbootcomongodb.entities.Post;
import com.michelabs.springbootcomongodb.repository.PostRepository;
import com.michelabs.springbootcomongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;		
	
	public Post findPostById(String id) {
		Optional<Post> post = postRepository.findById(id);		
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}
	
	public List<Post> findPostByTitle(String text) {
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);		
	}
}
