package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.PostComments;
import com.instagram.server.repository.PostRepo;
import com.instagram.server.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private PostRepo postRepo;
    private JwtUtil jwtUtil;

    @Autowired
    public PostServiceImpl(PostRepo postRepo,JwtUtil jwtUtil) {
        this.postRepo = postRepo;
        this.jwtUtil=jwtUtil;
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
    public Post commentOnPost(String id,String comment, String token) {
        String jwtToken = jwtUtil.getTokenFromRequest(token);
        Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
        String userId = claims.get("id").toString();
        Optional<Post> optionalPost = postRepo.findById(id);
        System.out.println("post is: "+id);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            PostComments postComments = new PostComments(userId,comment);
            post.addComment(postComments);
            postRepo.save(post);
            return post;
        }
        return null;
    }
}
