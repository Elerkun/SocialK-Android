package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Favorito;


public interface Favoritos_Repository extends MongoRepository<Favorito,String>{
	
	@Query("{email: ?0}")
	public List<Favorito> findFavoritobyUser_name(String email);

	@Query(value="{$and: [{'message.user.email':?0} ,{ 'message.message':?1}]}", delete=true)
	public void deletebyuser_email(String email, String message );
	
	
}
