package com.visionboard.web.service;

import com.visionboard.data.dto.VisionDto;
import com.visionboard.data.model.User;
import com.visionboard.data.model.Vision;
import com.visionboard.data.repository.UserRepository;
import com.visionboard.data.repository.VisionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisionBoardServiceImplTest {

    @Mock
    private VisionRepository visionRepository;

    @Mock
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private VisionBoardServiceImpl visionBoardService = new VisionBoardServiceImpl(modelMapper);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void visionCanBeCreated(){
        VisionDto visionDto = new VisionDto();
        String userId = "001";
        User user = new User();
        visionDto.setVision("This is my vision");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        visionBoardService.createVision(visionDto, userId);
        ArgumentCaptor<Vision> visionArgumentCaptor =ArgumentCaptor.forClass(Vision.class);

        verify(visionRepository, times(1)).save(visionArgumentCaptor.capture());
        Vision capturedVision =visionArgumentCaptor.getValue();
        assertThat(capturedVision.getVision()).isEqualTo(visionDto.getVision());

    }

    @Test
    void visionCanBeUpdated(){
        Vision vision = new Vision();
        vision.setVision("My vision");
        vision.setTitle("I will Win");
        vision.setVisionId("001");

        VisionDto visionToUpdate = new VisionDto();
        visionToUpdate.setVision("I won Already");

        when(visionRepository.findById(vision.getVisionId())).thenReturn(Optional.of(vision));
        visionBoardService.updateVision(visionToUpdate, vision.getVisionId());

        assertThat(vision.getVision()).isEqualTo(visionToUpdate.getVision());
    }


    @Test
    void testVisionCanBeFoundByTitle(){
        Vision vision = new Vision();

        vision.setVisionId("001");
        vision.setVision("New");
        vision.setTitle("New Year");

        when(visionRepository.findByTitle(vision.getTitle())).thenReturn(Optional.of(vision));
        visionBoardService.getVisionByTitle(vision.getTitle());

        assertThat(visionBoardService.getVisionByTitle(vision.getTitle())).isEqualTo(vision);
    }


    @Test
    void testYouCanGetAllVisions(){
        Vision vision1 = new Vision();
        Vision vision2 = new Vision();

        List<Vision> visions = new ArrayList<>();
        visions.add(vision1);
        visions.add(vision2);

        when(visionRepository.findAll()).thenReturn(visions);

        assertThat(visionBoardService.getAllVision()).hasSize(2);
    }

    @Test
    void testVisionCanBeFetchedWithId(){
        Vision vision = new Vision();
        vision.setVision("Vision");
        vision.setVisionId("9");

        when(visionRepository.findById(vision.getVisionId())).thenReturn(Optional.of(vision));
        visionBoardService.getVisionById(vision.getVisionId());

        assertThat(vision.getVision()).isEqualTo("Vision");

    }

    @Test
    void visionCanBeDeleted(){
        Vision vision = new Vision();
        vision.setVisionId("001");
        vision.setVision("My vision");

        when(visionRepository.findById(vision.getVisionId())).thenReturn(Optional.of(vision));
        visionBoardService.deleteVision(vision.getVisionId());

        verify(visionRepository).delete(vision);
    }

}