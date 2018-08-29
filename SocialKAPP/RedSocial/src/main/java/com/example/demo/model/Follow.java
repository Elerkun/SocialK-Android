package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Follows")
public class Follow {
	
	
	private String email;
	@Id
	private User user;
	
	
	
	
	public Follow() {
		super();
	}
	public Follow(String user_name, User user) {
		super();
		this.email = user_name;
		this.user = user;
	}
	public String getUser_name() {
		return email;
	}
	public void setUser_name(String user_name) {
		this.email = user_name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
