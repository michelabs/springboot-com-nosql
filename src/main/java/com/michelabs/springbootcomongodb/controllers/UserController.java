package com.michelabs.springbootcomongodb.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.michelabs.springbootcomongodb.dto.UserDTO;
import com.michelabs.springbootcomongodb.entities.Post;
import com.michelabs.springbootcomongodb.entities.User;
import com.michelabs.springbootcomongodb.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllUsers() {		
		List<User> listUsers = userService.findAllUsers();
		List<UserDTO> listDto = listUsers.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
		User user = userService.findUserById(id);
		return ResponseEntity.ok().body(new UserDTO(user)); 		
	}
	
	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody UserDTO userDTO) {
		User user = userService.userFromDTO(userDTO);
		user = userService.insertUser(user);
		URI userCreated = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(userCreated).body(user);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
		userService.deleteUserById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
		User user = userService.userFromDTO(userDTO);
		user.setId(id);
		user = userService.updateUser(user);
		return ResponseEntity.noContent().build();
	}	
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = userService.findUserById(id);
		return ResponseEntity.ok().body(user.getPosts()); 		
	}	
}
