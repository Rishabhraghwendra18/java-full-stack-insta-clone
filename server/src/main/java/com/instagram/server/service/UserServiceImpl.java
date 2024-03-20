package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.exceptions.AlreadyExistsException;
import com.instagram.server.exceptions.MissingFieldException;
import com.instagram.server.repository.UserRepo;
import com.instagram.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;
    private JwtUtil jwtUtil;
    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(UserRepo userRepo,JwtUtil jwtUtil) {

        this.userRepo = userRepo;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public User signUp(User user) {
        if(user.getUsername()==null){
            throw new MissingFieldException("Username is missing");
        }
        if(user.getEmail()==null){
            throw new MissingFieldException("Email is missing");
        }
        if(user.getPassword() == null){
            throw new MissingFieldException("Password is missing");
        }
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser !=null){
            throw new AlreadyExistsException("User already exists");
        }
        return userRepo.save(user);
    }

    @Override
    public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest) {
        if(jwtRequest.getEmail() == null){
            throw new MissingFieldException("Email is missing");
        }
        if(jwtRequest.getPassword() == null){
            throw new MissingFieldException("Password is missing");
        }
        User loggedInUser = userRepo.findByEmailAndPassword(jwtRequest.getEmail(),jwtRequest.getPassword());
        System.out.println("Sign in: "+loggedInUser);
        if(loggedInUser != null){
            String token = jwtUtil.generateToken(loggedInUser);
            JwtResponse response = new JwtResponse(token,loggedInUser.getUsername());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        JwtResponse response = new JwtResponse(null,null);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @Override
    public String post(Post post) {
        return null;
    }
}
