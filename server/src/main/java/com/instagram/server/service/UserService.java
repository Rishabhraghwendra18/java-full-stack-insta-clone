package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.requestResponse.CommonResponse;
import com.instagram.server.requestResponse.JwtRequest;
import com.instagram.server.requestResponse.JwtResponse;
import com.instagram.server.requestResponse.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    public ResponseEntity<CommonResponse> signUp(SignUpRequest user);
    public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest);
    public ResponseEntity<CommonResponse> post(MultipartFile file, String text, String jwtToken);
}
