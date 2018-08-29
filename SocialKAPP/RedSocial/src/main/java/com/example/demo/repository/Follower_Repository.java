package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Follower;

public interface Follower_Repository extends MongoRepository<Follower, String >{
	
	@Query("{email: ?0}")
	public List<Follower> findFollowers(String email);
	
	

}
