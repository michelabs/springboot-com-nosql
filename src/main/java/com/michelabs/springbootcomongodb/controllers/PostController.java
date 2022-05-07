package com.michelabs.springbootcomongodb.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michelabs.springbootcomongodb.controllers.util.URL;
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
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = postService.findPostByTitle(text);
		return ResponseEntity.ok().body(list); 			
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date(0L));
		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list); 			
	}
}
