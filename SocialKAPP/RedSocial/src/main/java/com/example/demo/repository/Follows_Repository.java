package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Follow;
import com.example.demo.model.Follower;
import com.example.demo.model.User;

public interface Follows_Repository extends MongoRepository<Follow, String>{
	
	@Query("{email: ?0}")
	public List<Follow> findFollows(String email);
	
	@Query(value="{'user.email': ?0}", delete=true)
	public void deletebyuser_email(String email);
	

}
