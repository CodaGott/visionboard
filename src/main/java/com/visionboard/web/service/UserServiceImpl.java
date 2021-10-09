package com.visionboard.web.service;

import com.visionboard.data.dto.UserDto;
import com.visionboard.data.model.Role;
import com.visionboard.data.model.User;
import com.visionboard.data.repository.UserRepository;
import com.visionboard.exception.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User createAdmin(UserDto userDto) {
        User user = new User();

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new UserException("Admin with email already exists");
        }else {
            modelMapper.map(userDto, user);
            user.setRoles(Role.ADMIN);
            return userRepository.save(user);
        }
    }

    @Override
    public User createUser(UserDto userDto) {

        User user = new User();

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new UserException("User with email already exists");
        }else {
            modelMapper.map(userDto, user);
            user.setRoles(Role.USER);
            return userRepository.save(user);
        }
    }

    @Override
    public Optional<User> getAUserByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getAUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getAUserById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public User updateUser(User user, String userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
