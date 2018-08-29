package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Follow;
import com.example.demo.model.Follower;
import com.example.demo.repository.Follower_Repository;


@RestController
@RequestMapping("/Follower")
public class Follower_Controller {
	
	@Autowired 
	Follower_Repository follower_Repository;
	
	@RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void save (@RequestBody Follower follower) {
		follower_Repository.save(follower);
		
	}
	@RequestMapping(value ="findAll/{email}",method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Follower> findAll (@PathVariable String email){
		return follower_Repository.findFollowers(email);
		
		
	}
	@RequestMapping(value="unfollower/",method = RequestMethod.DELETE)
	public void delete(@RequestBody Follower follower) {
		 follower_Repository.delete(follower);
	}

}
