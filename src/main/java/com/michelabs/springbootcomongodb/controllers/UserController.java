package com.michelabs.springbootcomongodb.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelabs.springbootcomongodb.dto.UserDTO;
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
	
}
