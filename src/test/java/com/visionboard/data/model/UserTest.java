package com.visionboard.data.model;

import com.visionboard.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userCanBeCreated(){
        User user = new User();

        user.setName("Dozie");
        user.setEmail("doz@mail.com");
        user.setAge("19");
        userRepository.save(user);

        assertEquals(user.getName(), "Dozie");
    }

    @Test
    void userCanBeFound(){
        User user = new User();

        user.setName("Dozie");
        user.setEmail("doz@mail.com");
        user.setAge("19");
        userRepository.save(user);

//        assertEquals(userRepository.findByAge(user.getAge()), Optional.of(user));
    }

    @Test
    void userInfoCanBeUpdated(){
        User user = new User();

        user.setName("Dozie");
        user.setEmail("doz@mail.com");
        user.setAge("19");
        userRepository.save(user);

        user.setEmail("doz@mailme.com");
        userRepository.save(user);

        assertNotNull(user.getName());
        assertEquals(user.getEmail(), "doz@mailme.com");
    }

    @Test
    void userCanBeDeleted(){
        User user = new User();

        user.setName("Dozie");
        user.setEmail("doz@mail.com");
        user.setAge("19");
        userRepository.save(user);

        userRepository.delete(user);
        assertEquals(0,userRepository.findAll().size());
    }

}