package com.visionboard.web.service;

import com.visionboard.data.dto.VisionDto;
import com.visionboard.data.model.Vision;
import com.visionboard.exception.VisionBoardException;

import java.util.List;
import java.util.Optional;

public interface VisionBoardService {
    Optional<Vision> getVisionById(String id) throws VisionBoardException;
    Vision getVisionByTitle(String title) throws VisionBoardException;
    List<Vision> getAllVision();
    Vision createVision(VisionDto visionDto, String userId) throws VisionBoardException;
    Vision updateVision(VisionDto visionDto, String visionId) throws VisionBoardException;
    void deleteVision(String id);
}
