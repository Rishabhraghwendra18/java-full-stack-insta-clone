package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public User signUp(User user);
    public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest);
    public String post(Post post);
}
