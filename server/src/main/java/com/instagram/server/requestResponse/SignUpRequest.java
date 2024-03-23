package com.instagram.server.requestResponse;

public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String profilePhoto;
    public SignUpRequest(){}

    public SignUpRequest(String username, String email, String password, String profilePhoto) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
