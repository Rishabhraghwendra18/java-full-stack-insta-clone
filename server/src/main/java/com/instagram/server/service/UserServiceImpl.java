package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.exceptions.AlreadyExistsException;
import com.instagram.server.exceptions.MissingFieldException;
import com.instagram.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;
    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
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
    public User signIn(User user) {
        if(user.getEmail() == null){
            throw new MissingFieldException("Email is missing");
        }
        if(user.getPassword() == null){
            throw new MissingFieldException("Password is missing");
        }
        return userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }

    @Override
    public String post(Post post) {
        return null;
    }
}
