package com.michelabs.springbootcomongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelabs.springbootcomongodb.entities.User;
import com.michelabs.springbootcomongodb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
}
