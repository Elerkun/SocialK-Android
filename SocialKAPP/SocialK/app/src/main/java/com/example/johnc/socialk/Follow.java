package com.example.johnc.socialk;

public class Follow {

    private String email;
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
