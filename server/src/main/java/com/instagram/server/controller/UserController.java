package com.instagram.server.controller;

import com.instagram.server.collection.User;
import com.instagram.server.exceptions.AlreadyExistsException;
import com.instagram.server.exceptions.ErrorResponse;
import com.instagram.server.exceptions.MissingFieldException;
import com.instagram.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
