package com.michelabs.springbootcomongodb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelabs.springbootcomongodb.entities.Post;
import com.michelabs.springbootcomongodb.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findPostById(@PathVariable String id) {
		Post post = postService.findPostById(id);
		return ResponseEntity.ok().body(post); 		
	}
}
