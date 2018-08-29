package com.example.johnc.socialk;

public class Follower {

    private String email;
    private User user;




    public Follower() {
        super();
    }
    public Follower(String user_name, User user) {
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
