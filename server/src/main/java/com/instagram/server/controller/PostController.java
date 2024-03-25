package com.instagram.server.controller;

import com.instagram.server.collection.Post;
import com.instagram.server.requestResponse.CommonResponse;
import com.instagram.server.requestResponse.PostLikeRequest;
import com.instagram.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService=postService;
    }
    @GetMapping("/")
    public List<Post> getAllPosts(){
        return postService.feed();
    }
    @PostMapping("/like/{postId}")
    public ResponseEntity<CommonResponse> likePost(@PathVariable String postId, @RequestBody PostLikeRequest postLikeRequest){
        Post post = postService.likePost(postId,postLikeRequest.getIsLiked());
        if (post == null){
            return new ResponseEntity<>(new CommonResponse("Error while liking the post", true,HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new CommonResponse("Updated post engagement successfully",false,HttpStatus.OK.value()),HttpStatus.OK);
    }
}
