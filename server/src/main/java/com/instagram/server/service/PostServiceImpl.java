package com.instagram.server.service;

import com.instagram.server.collection.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public List<Post> feed() {
        return null;
    }

    @Override
    public void likePost() {

    }

    @Override
    public void commentOnPost(String comment) {

    }
}
