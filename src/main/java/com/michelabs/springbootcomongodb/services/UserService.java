package com.michelabs.springbootcomongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelabs.springbootcomongodb.dto.UserDTO;
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
	
	public User insertUser(User user) {
		return userRepository.insert(user);
	}
	
	public void deleteUserById(String id) {
		findUserById(id);
		userRepository.deleteById(id);
	}
	
	public User userFromDTO (UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());		
	}
}
