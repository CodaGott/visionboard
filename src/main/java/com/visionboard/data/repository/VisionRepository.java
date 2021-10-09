package com.visionboard.data.repository;

import com.visionboard.data.model.Category;
import com.visionboard.data.model.Vision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VisionRepository extends MongoRepository<Vision, String > {

    Optional<Vision> findByTitle(String title);
    List<Vision> findByCategory(Category category);
}
