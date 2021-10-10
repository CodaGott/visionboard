package com.visionboard.web.service;

import com.visionboard.data.dto.UserDto;
import com.visionboard.data.model.User;
import com.visionboard.data.repository.UserRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(modelMapper);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUserCanBeCreated(){
        User user = new User();
        UserDto userDto = new UserDto();

        user.setUserId("001");

        userDto.setEmail("user@email.com");
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        userService.createUser(userDto);

        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    void testUserInfoCanBeUpdated(){
        User user = new User();
        user.setUserId("001");
        user.setEmail("user@email.com");

        UserDto userToUpdate = new UserDto();
        userToUpdate.setEmail("user1@email.com");

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        userService.updateUser(userToUpdate, user.getUserId());

        assertThat(user.getEmail()).isEqualTo(userToUpdate.getEmail());
    }

    @Test
    void testUserCanBeFoundByName(){
        User user = new User();
        user.setUserId("001");
        user.setEmail("user@email.com");
        user.setName("John Doe");

        when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));
        userService.getAUserByName(user.getName());

        assertThat(user.getName()).isEqualTo("John Doe");
    }

    @Test
    void testUserCanBeFoundGivenAnEmail(){
        User user = new User();
        user.setUserId("001");
        user.setEmail("user@email.com");
        user.setName("John Doe");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        userService.getAUserByEmail(user.getEmail());

        assertThat(user.getEmail()).isEqualTo("user@email.com");
    }

    @Test
    void testUserCanBeFoundGivenAnId(){
        User user = new User();
        user.setUserId("001");
        user.setEmail("user@email.com");
        user.setName("John Doe");

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        userService.getAUserById(user.getUserId());

        assertThat(user.getEmail()).isEqualTo("user@email.com");
    }

    @Test
    void testAllUsersCanBeFound(){
        User user1 = new User();
        User user2 = new User();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        assertThat(userService.getAllUsers()).hasSize(2);

    }

    @Test
    void testThatUserCanBeDeleted(){
        User user = new User();
        user.setUserId("001");
        user.setEmail("user@email.com");
        user.setName("John Doe");

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        userService.deleteUser(user.getUserId());

        verify(userRepository).delete(user);
    }

}