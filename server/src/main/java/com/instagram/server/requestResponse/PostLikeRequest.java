package com.instagram.server.requestResponse;

public class PostLikeRequest {
    private boolean isLiked;

    public PostLikeRequest() {
    }
    public PostLikeRequest(boolean isLiked) {
        this.isLiked=isLiked;
    }

    public boolean getIsLiked(){
        return isLiked;
    }
    public void setIsLiked(boolean liked){
        isLiked=liked;
    }
}
