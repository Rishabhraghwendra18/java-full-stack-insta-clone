package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User signUp(User user);
    public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest);
    public String post(Post post);
}
