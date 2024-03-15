package com.instagram.server.collection;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EntityScan
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String photoUrl;
    private String caption;
    private int likes;
    private List<String> comments;
    private String userId;
    public Post(){}

    public Post(String photoUrl, String caption, int likes, List<String> comments, String userId) {
        this.photoUrl = photoUrl;
        this.caption = caption;
        this.likes = likes;
        this.comments = comments;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", caption='" + caption + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                ", userId='" + userId + '\'' +
                '}';
    }
}
