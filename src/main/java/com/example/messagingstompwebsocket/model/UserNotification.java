package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.model.User;

import java.time.LocalDateTime;

public class UserNotification {
    private String action;  // "CREATE", "UPDATE", "DELETE"
    private User user;
    private LocalDateTime timestamp;

    public UserNotification() {}

    public UserNotification(String action, User user) {
        this.action = action;
        this.user = user;
    }

    public UserNotification(String action, User user, LocalDateTime timestamp) {
        this.action = action;
        this.user = user;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}