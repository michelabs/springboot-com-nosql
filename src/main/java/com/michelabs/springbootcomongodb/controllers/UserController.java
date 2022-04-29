package com.michelabs.springbootcomongodb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelabs.springbootcomongodb.entities.User;
import com.michelabs.springbootcomongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {		
		List<User> listUsers = userService.findAllUsers();
		return ResponseEntity.ok().body(listUsers);
	}	
	
}
