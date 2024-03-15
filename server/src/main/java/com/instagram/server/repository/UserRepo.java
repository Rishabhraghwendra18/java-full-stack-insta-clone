package com.instagram.server.repository;

import com.instagram.server.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
}
