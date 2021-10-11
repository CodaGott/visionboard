package com.visionboard.web.controller;


import com.visionboard.data.dto.VisionDto;
import com.visionboard.exception.VisionBoardException;
import com.visionboard.web.response.ApiResponse;
import com.visionboard.web.service.VisionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vision")
public class VisionController {


    @Autowired
    private VisionBoardService visionService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> createVision(@RequestBody @Valid VisionDto visionDto, @PathVariable String userId){
        try{
            visionService.createVision(visionDto, userId);
            return new ResponseEntity<>(new ApiResponse(true, "Vision created successfully"), HttpStatus.CREATED);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{visionId}")
    public ResponseEntity<?> updateInfo(@RequestBody VisionDto visionDto, @PathVariable String visionId){
        try {
            visionService.updateVision(visionDto, visionId);
            return new ResponseEntity<>(new ApiResponse(true, "Update successfully"), HttpStatus.ACCEPTED);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllVisions(){
        try {
            visionService.getAllVision();
            return new ResponseEntity<>(new ApiResponse(true, "Found " + visionService.getAllVision()), HttpStatus.FOUND);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{visionId}")
    public ResponseEntity<?> deleteVision(@PathVariable String visionId){
        try {
            visionService.deleteVision(visionId);
            return new ResponseEntity<>(new ApiResponse(true, "Vision deleted successfully"), HttpStatus.NO_CONTENT);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{visionId}")
    public ResponseEntity<?> getVisionById(@PathVariable String visionId){
        try {
            visionService.getVisionById(visionId);
            return new ResponseEntity<>(new ApiResponse
                    (true, "Vision with " +visionService.getVisionById(visionId) + " found"), HttpStatus.FOUND);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByName/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){
        try {
            visionService.getVisionByTitle(title);
            return new ResponseEntity<>(new ApiResponse
                    (true, "Vision with title " + visionService.getVisionByTitle(title) + " found"), HttpStatus.FOUND);
        }catch (VisionBoardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
