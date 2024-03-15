package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;

public interface UserService {
    public User signUp(User user);
    public User signIn(User user);
    public String post(Post post);
}
