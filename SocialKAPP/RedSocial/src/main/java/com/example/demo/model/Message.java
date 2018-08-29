package com.example.demo.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Message")
public class Message {
	
	private String message;
	private String image;
	private User user;
	
	
	
	
	
	public Message() {
		super();
	}
	public Message(String message, String image, User user) {
		super();
		this.message = message;
		this.image = image;
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

}
