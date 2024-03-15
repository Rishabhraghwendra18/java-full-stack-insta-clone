package com.instagram.server.repository;

import com.instagram.server.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
}
