package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.User;

public interface User_Repository extends MongoRepository<User,String> {
	
	@Query("{user_name: ?0}")
	public List<User> findUserbyUser_name(String user_name);
	
	@Query("{email: ?0 }")
	public User findUser(String email);
	
	


}