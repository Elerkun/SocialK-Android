package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Follow;
import com.example.demo.model.Follower;
import com.example.demo.model.User;
import com.example.demo.repository.Follows_Repository;

@RestController
@RequestMapping("/Follow")
public class Follow_Controller {

	
	@Autowired
	Follows_Repository follows_Repository;
	
	
	@RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Follow follow) {
		follows_Repository.save(follow);
		
	}
	@RequestMapping(value="unfollow/{email}",method = RequestMethod.DELETE, consumes = MediaType.ALL_VALUE)
	public void delete(@PathVariable String email) {
		 follows_Repository.deletebyuser_email(email);
	}
	@RequestMapping(value ="findAll/{email}",method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Follow> findAll (@PathVariable String email){
		return follows_Repository.findFollows(email);
		
		
	}
}
