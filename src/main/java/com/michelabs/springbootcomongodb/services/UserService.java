package com.michelabs.springbootcomongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelabs.springbootcomongodb.entities.User;
import com.michelabs.springbootcomongodb.repository.UserRepository;
import com.michelabs.springbootcomongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User findUserById(String id) {
		Optional<User> user = userRepository.findById(id);		
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}
}
