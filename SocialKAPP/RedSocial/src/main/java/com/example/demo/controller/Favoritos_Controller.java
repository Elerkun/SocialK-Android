package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Favorito;

import com.example.demo.repository.Favoritos_Repository;


@RestController
@RequestMapping("/Favorito")
public class Favoritos_Controller {
	
	
	@Autowired
	Favoritos_Repository favoritos_Repository;
	
	@RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Favorito favorito) {
		favoritos_Repository.save(favorito);
		
	}
	
	@RequestMapping(value ="findAll/{email}",method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public List<Favorito> findAll (@PathVariable String email){
		return favoritos_Repository.findFavoritobyUser_name(email);
		
		
	}
	@RequestMapping(value="unliked/{email}/{message}",method = RequestMethod.DELETE, consumes = MediaType.ALL_VALUE)
	public void delete(@PathVariable(value="email") String email,@PathVariable(value="message") String message) {
		 favoritos_Repository.deletebyuser_email(email, message);
	}

}
