package com.instagram.server.requestResponse;

public class PostLikeRequest {
    private boolean isLiked;
    private String postId;

    public PostLikeRequest() {
    }
    public PostLikeRequest(boolean isLiked,String postId) {
        this.isLiked=isLiked;
        this.postId=postId;
    }

    public boolean getIsLiked(){
        return isLiked;
    }
    public void setIsLiked(boolean liked){
        isLiked=liked;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
