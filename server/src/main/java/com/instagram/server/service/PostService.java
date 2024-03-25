package com.instagram.server.service;

import com.instagram.server.collection.Post;

import java.util.List;

public interface PostService {
    public List<Post> feed();
    public Post likePost(String id, boolean isLiked);
    public Post commentOnPost(String id,String comment, String token);
}
