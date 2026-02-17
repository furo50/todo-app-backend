package com.mehmet.furkan.ozer.todo_backend.dto;

public class AuthResponse {

    private String token;
    private String username;

    // Constructors
    public AuthResponse() {
    }

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}