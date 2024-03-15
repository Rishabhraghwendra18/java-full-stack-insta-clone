package com.instagram.server.repository;

import com.instagram.server.collection.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String> {
}
