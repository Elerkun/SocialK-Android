package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.User_Repository;

@RestController
@RequestMapping("/User")
public class User_Controller {
	
	@Autowired
	User_Repository user_Repository;
	
	@RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		user_Repository.insert(user);
		
	}
	@RequestMapping(value="findAll/{user_name}", method = RequestMethod.GET, consumes= MediaType.ALL_VALUE)
	public List<User> findAll (@PathVariable String user_name){
		return user_Repository.findUserbyUser_name(user_name);
	}
	
	@RequestMapping (value="findOne/{email}", method= RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public User findOne (@PathVariable String email ) {
		return user_Repository.findUser(email); 
	}
	
	//Delete account
	@RequestMapping(value="delete/{id}", method= RequestMethod.DELETE)
	public void deleteUser (@PathVariable String id) {
		user_Repository.deleteById(id);
	}

}
