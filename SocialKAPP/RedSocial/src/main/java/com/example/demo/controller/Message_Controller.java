package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;
import com.example.demo.repository.Messages_Repository;

@RestController
@RequestMapping("/Message")
public class Message_Controller {

	
	@Autowired 
	Messages_Repository messages_Repository;
	
	@RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Message message) {
		messages_Repository.save(message);
		
	}
	@RequestMapping(value="findAll/{user_name}", method = RequestMethod.GET, consumes= MediaType.ALL_VALUE)
	public List<Message> findAllbyUser_email (@PathVariable String user_name ){
		return messages_Repository.findMessagebyUser(user_name);
		
	}
	
	
	@RequestMapping(value ="findAll/", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Message> findAll (){
		return messages_Repository.findAll();
	}
}
