package com.visionboard.web.service;

import com.visionboard.data.dto.UserDto;
import com.visionboard.data.model.User;
import com.visionboard.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createAdmin(UserDto userDto) throws UserException;
    User createUser(UserDto userDto)throws UserException;
    Optional<User> getAUserByName(String name)throws UserException;
    Optional<User> getAUserByEmail(String email) throws UserException;
    Optional<User> getAUserById(String id) throws UserException;
    void deleteUser(String id) throws UserException;
    User updateUser(UserDto userDto, String userId) throws UserException;
    List<User> getAllUsers();
}
