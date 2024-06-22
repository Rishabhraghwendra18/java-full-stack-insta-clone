package com.instagram.server.requestResponse;

public class PostCommentRequest {
    private String comment;
    private String postId;
    public PostCommentRequest() {}
    public PostCommentRequest(String comment,String postId) {
        this.comment = comment;
        this.postId=postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
