package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private PostRepo postRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<Post> feed() {
    List<Post> posts = postRepo.findAll();
    return posts;
    }

    @Override
    public void likePost() {

    }

    @Override
    public void commentOnPost(String comment) {

    }
}
