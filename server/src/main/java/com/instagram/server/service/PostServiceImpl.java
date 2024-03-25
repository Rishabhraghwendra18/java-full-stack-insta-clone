package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private PostRepo postRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<Post> feed() {
        return postRepo.findAll();
    }

    @Override
    public Post likePost(String id, boolean isLiked) {
        Optional<Post> optionalPost=postRepo.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            if(isLiked){
                post.setLikes(post.getLikes()+1);
            }
            else if (post.getLikes() -1 > 0){
                post.setLikes(post.getLikes()-1);
            }
            else{
                post.setLikes(0);
            }
            postRepo.save(post);
            return post;
        }
        return null;
    }

    @Override
    public void commentOnPost(String comment) {

    }
}
