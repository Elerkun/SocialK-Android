package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Message;

public interface Messages_Repository  extends MongoRepository<Message, String>{
	
	@Query("{'user.email': ?0}")
	public List<Message> findMessagebyUser(String user_name);

}
