package com.visionboard.web.service;

import com.visionboard.data.dto.VisionDto;
import com.visionboard.data.model.User;
import com.visionboard.data.model.Vision;
import com.visionboard.data.repository.UserRepository;
import com.visionboard.data.repository.VisionRepository;
import com.visionboard.exception.VisionBoardException;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public VisionBoardServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Vision> getVisionById(String id) throws VisionBoardException {
        return visionRepository.findById(id);
    }

    @Override
    public Vision getVisionByTitle(String title) throws VisionBoardException {
        return visionRepository.findByTitle(title).orElseThrow(() ->
                new VisionBoardException("Vision with title does not exist"));
    }

    @Override
    public List<Vision> getAllVision() {
        return visionRepository.findAll();
    }

    @Override
    public Vision createVision(VisionDto visionDto, String userId) throws VisionBoardException {

        Vision vision = new Vision();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            modelMapper.map(visionDto, vision);
            User user = optionalUser.get();
            Vision savedVision = visionRepository.save(vision);
            user.addVision(savedVision);
            userRepository.save(user);
            return savedVision;
        }else {
            throw new VisionBoardException("Can't create vision without a user");
        }
    }

    @Override
    public Vision updateVision(VisionDto visionDto, String visionId) throws VisionBoardException {
        Vision vision = visionRepository.findById(visionId).orElseThrow(() ->
                new VisionBoardException("vision with id not found"));
        modelMapper.map(visionDto, vision);
        return visionRepository.save(vision);
    }

    @Override
    public void deleteVision(String id) {
        Vision vision = visionRepository.findById(id).orElseThrow(() ->
                new VisionBoardException("Can't find vision with given id"));
        visionRepository.delete(vision);
    }
}
