package com.michelabs.springbootcomongodb.services;

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
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
}
