package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String signUp(User user) {
        return null;
    }

    @Override
    public String signIn(User user) {
        return null;
    }

    @Override
    public String post(Post post) {
        return null;
    }
}
