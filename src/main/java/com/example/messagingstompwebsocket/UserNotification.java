package com.example.messagingstompwebsocket;

public class UserNotification {
    private String action;  // "CREATE", "UPDATE", "DELETE"
    private User user;

    public UserNotification() {}

    public UserNotification(String action, User user) {
        this.action = action;
        this.user = user;
    }

    // Getters and Setters
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}