package com.instagram.server.collection;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EntityScan
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String photoUrl;
    private String fileName;
    private String caption;
    private int likes;
    private List<PostComments> comments;
    private String userId;
    public Post(){
        this.likes=0;
        this.comments= new ArrayList<>();
    }

    public Post(String photoUrl, String fileName,String caption, int likes, List<PostComments> comments, String userId) {
        this.photoUrl = photoUrl;
        this.fileName = fileName;
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

    public List<PostComments> getComments() {
        return comments;
    }

    public void setComments(List<PostComments> comments) {
        this.comments = comments;
    }
    public void addComment(PostComments comment){
        this.comments.add(comment);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
