package com.visionboard.web.service;

import com.visionboard.data.dto.UserDto;
import com.visionboard.data.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createAdmin(UserDto userDto);
    User createUser(UserDto userDto);
    Optional<User> getAUserByName(String name);
    Optional<User> getAUserByEmail(String email);
    Optional<User> getAUserById(String id);
    void deleteUser(String id);
    User updateUser(User user, String userId);
    List<User> getAllUsers();
}
