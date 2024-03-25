package com.instagram.server.collection;

public class PostComments{
    private String userId;
    private String comment;

    public PostComments(String userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
