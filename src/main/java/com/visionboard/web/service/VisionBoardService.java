package com.visionboard.web.service;

import com.visionboard.data.model.Vision;
import com.visionboard.exception.VisionBoardException;

import java.util.List;
import java.util.Optional;

public interface VisionBoardService {
    Optional<Vision> getVisionById(String id) throws VisionBoardException;
    Optional<Vision> getVisionByTitle(String title) throws VisionBoardException;
    List<Vision> getAllVision();
    Vision createVision(Vision vision, String userId) throws VisionBoardException;
    Vision updateVision(Vision vision) throws VisionBoardException;
    void deleteVision(String id);
}
