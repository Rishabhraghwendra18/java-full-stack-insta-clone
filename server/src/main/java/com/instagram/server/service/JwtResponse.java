package com.instagram.server.service;

public class JwtResponse {
    private String token;
    private String username;
    private String message;
    public JwtResponse(){}

    public JwtResponse(String token, String username, String message) {
        this.token = token;
        this.username = username;
        this.message=message;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
