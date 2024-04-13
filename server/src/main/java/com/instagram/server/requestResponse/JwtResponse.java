package com.instagram.server.requestResponse;

import java.util.Date;

public class JwtResponse {
    private String token;
    private String username;
    private String message;
    private Date expirationDate;

    public JwtResponse(){}

    public JwtResponse(String token, String username, String message, Date expirationDate) {
        this.token = token;
        this.username = username;
        this.message=message;
        this.expirationDate=expirationDate;
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
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
