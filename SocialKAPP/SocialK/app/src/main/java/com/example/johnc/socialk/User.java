package com.example.johnc.socialk;

public class User {

    private String user_name;
    private String pass;
    private String email;
    private String profile_image;
    private String user;
    private String user_nameUser;
    private String profile_imageUser;

    public User() {
        super();
    }


    public User(String user_name, String pass, String email, String profile_image, String user, String user_nameUser, String profile_imageUser) {
        this.user_name = user_name;
        this.pass = pass;
        this.email = email;
        this.profile_image = profile_image;
        this.user = user;
        this.user_nameUser = user_nameUser;
        this.profile_imageUser = profile_imageUser;
    }

    public String getProfile_imageUser() {
        return profile_imageUser;
    }

    public void setProfile_imageUser(String profile_imageUser) {
        this.profile_imageUser = profile_imageUser;
    }

    public String getUser_nameUser() {
        return user_nameUser;
    }

    public void setUser_nameUser(String user_nameUser) {
        this.user_nameUser = user_nameUser;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
