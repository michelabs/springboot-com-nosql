package com.michelabs.springbootcomongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.michelabs.springbootcomongodb.dto.AuthorDTO;
import com.michelabs.springbootcomongodb.entities.Post;
import com.michelabs.springbootcomongodb.entities.User;
import com.michelabs.springbootcomongodb.repository.PostRepository;
import com.michelabs.springbootcomongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com");
		User user2 = new User(null, "Alex Green", "alex@gmail.com");
		User user3 = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));		
		
		postRepository.deleteAll();
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para SP. Abraçoes!", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz!", new AuthorDTO(user1));		
		
		postRepository.saveAll(Arrays.asList(post1, post2));		
		
	}
}
