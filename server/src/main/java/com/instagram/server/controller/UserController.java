package com.instagram.server.controller;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.exceptions.AlreadyExistsException;
import com.instagram.server.exceptions.ErrorResponse;
import com.instagram.server.exceptions.MissingFieldException;
import com.instagram.server.service.JwtRequest;
import com.instagram.server.service.JwtResponse;
import com.instagram.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user){
        return userService.signUp(user);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody JwtRequest user){
        return userService.signIn(user);
    }
    @PostMapping("/post")
    public Post uploadPost(@RequestBody Post post){
        List<String>comments = new ArrayList<>();
        comments.add("Nice post");
        Post demoPost = new Post("google.com","It's google",0,comments,"abced");
        return demoPost;
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MissingFieldException exec){
        ErrorResponse err = new ErrorResponse(exec.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<ErrorResponse>(err,HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AlreadyExistsException exec){
        ErrorResponse err = new ErrorResponse(exec.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<ErrorResponse>(err,HttpStatus.CONFLICT);
    }
}
