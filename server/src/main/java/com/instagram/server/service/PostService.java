package com.instagram.server.service;

import com.instagram.server.collection.Post;

import java.util.List;

public interface PostService {
    public List<Post> feed();
    public void likePost();
    public void commentOnPost(String comment);
}
