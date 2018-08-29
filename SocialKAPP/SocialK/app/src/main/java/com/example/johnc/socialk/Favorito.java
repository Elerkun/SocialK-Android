package com.example.johnc.socialk;

public class Favorito {
    private String email;
    private Message message;


    public Favorito() {
        super();
    }
    public Favorito(String email, Message message) {
        super();
        this.email = email;
        this.message = message;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

}
