package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;

public interface UserService {
    public String signUp(User user);
    public String signIn(User user);
    public String post(Post post);
}
