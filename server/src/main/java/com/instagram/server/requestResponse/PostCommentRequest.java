package com.instagram.server.requestResponse;

public class PostCommentRequest {
    private String comment;
    public PostCommentRequest() {}
    public PostCommentRequest(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
