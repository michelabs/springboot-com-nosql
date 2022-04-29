package com.michelabs.springbootcomongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.michelabs.springbootcomongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	
}
