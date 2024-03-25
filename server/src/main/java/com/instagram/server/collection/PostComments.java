package com.instagram.server.collection;

public class PostComments{
    private int userId;
    private String comment;

    public PostComments(int userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
