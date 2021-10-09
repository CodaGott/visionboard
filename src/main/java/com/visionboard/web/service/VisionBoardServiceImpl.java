package com.visionboard.web.service;

import com.visionboard.data.model.Vision;
import com.visionboard.data.repository.UserRepository;
import com.visionboard.data.repository.VisionRepository;
import com.visionboard.exception.VisionBoardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisionBoardServiceImpl implements VisionBoardService{

    @Autowired
    private VisionRepository visionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Vision> getVisionById(String id) throws VisionBoardException {
        return Optional.empty();
    }

    @Override
    public Optional<Vision> getVisionByTitle(String title) throws VisionBoardException {
        return Optional.empty();
    }

    @Override
    public List<Vision> getAllVision() {
        return null;
    }

    @Override
    public Vision createVision(Vision vision, String userId) throws VisionBoardException {
        return null;
    }

    @Override
    public Vision updateVision(Vision vision) throws VisionBoardException {
        return null;
    }

    @Override
    public void deleteVision(String id) {

    }
}
