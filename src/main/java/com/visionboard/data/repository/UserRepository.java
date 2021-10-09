package com.visionboard.data.repository;

import com.visionboard.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String > {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByAge(String age);
}
