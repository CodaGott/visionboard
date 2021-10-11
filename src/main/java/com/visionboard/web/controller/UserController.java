package com.visionboard.web.controller;

import com.visionboard.data.dto.UserDto;
import com.visionboard.exception.UserException;
import com.visionboard.web.response.ApiResponse;
import com.visionboard.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/adminSignUp")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid UserDto userDto){
        try {
            userService.createAdmin(userDto);
            return new ResponseEntity<>(new ApiResponse(true, "Admin account created successfully"), HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/userSignUp")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto){
        try {
            userService.createAdmin(userDto);
            return new ResponseEntity<>(new ApiResponse(true, "User account created successfully"), HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto, @PathVariable String userId){
        try {
            userService.updateUser(userDto, userId);
            return new ResponseEntity<>(new ApiResponse(true, "User info updated successfully"), HttpStatus.OK);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId){
        try {
            userService.getAUserById(userId);
            return new ResponseEntity<>(new ApiResponse(true, "User found"), HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userByName/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        try {
            userService.getAUserByName(name);
            return new ResponseEntity<>(new ApiResponse(true, "User with " + name + "found"), HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(new ApiResponse(true, "User deleted successfully"), HttpStatus.NO_CONTENT);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers(){
        try {
            userService.getAllUsers();
            return new ResponseEntity<>(new ApiResponse(true, "Users found " + userService.getAllUsers()), HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try {
            userService.getAUserByEmail(email);
            return new ResponseEntity<>(new ApiResponse(true, "User with email " + email+ " found"), HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
