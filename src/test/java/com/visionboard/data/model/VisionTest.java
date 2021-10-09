package com.visionboard.data.model;

import com.visionboard.data.repository.VisionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisionTest {

    @Autowired
    private VisionRepository visionRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void visionCanBeCreated(){
        Vision vision = new Vision();
        vision.setTitle("My vision 2021");
        vision.setVision("This vision is to motivate me!");
//        vision.setCategory(Category.CAREER);
        visionRepository.save(vision);
        assertEquals(1, visionRepository.findAll().size());
    }

    @Test
    void visionCanBeRead(){
        Vision vision = new Vision();
        vision.setTitle("My vision 2021");
        vision.setVision("This vision is to motivate me!");
//        vision.setCategory(Category.CAREER);
        visionRepository.save(vision);
        assertEquals("My vision 2021", vision.getTitle());
    }

    @Test
    void visionCanBeUpdated(){
        Vision vision = new Vision();
        vision.setTitle("My vision 2021");
        vision.setVision("This vision is to motivate me!");
        visionRepository.save(vision);
        vision.setTitle("My vision June 2021");
        visionRepository.save(vision);
        assertEquals("My vision June 2021", vision.getTitle());
    }

    @Test
    void visionCanBeDeleted(){
        Vision vision = new Vision();
        vision.setTitle("My vision 2021");
        vision.setVision("This vision is to motivate me!");
//        vision.setCategory(Category.CAREER);
        visionRepository.save(vision);
        visionRepository.delete(vision);
        assertEquals(0, visionRepository.findAll().size());
    }
}