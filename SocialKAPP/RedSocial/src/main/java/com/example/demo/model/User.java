package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class User{
	
	private String user_name;
    private String pass;
    @Id
    private String email;
    private String profile_image;
    
	public User() {
		super();
	}

	public User(String user_name, String pass, String email, String profile_image) {
		super();
		this.user_name = user_name;
		this.pass = pass;
		this.email = email;
		this.profile_image = profile_image;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
    
	
	
    
    
    
   

}
