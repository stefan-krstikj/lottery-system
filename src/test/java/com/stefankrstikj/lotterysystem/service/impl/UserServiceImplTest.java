package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.mapper.UserMapper;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;
import com.stefankrstikj.lotterysystem.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.stefankrstikj.lotterysystem.data.UserDummyData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @AfterEach
    void tearDown() {
        userService = null;
    }

    @Test
    void loadUserByUsernameReturnsUser() {
        when(userRepository.findByUsername("firstname")).thenReturn(Optional.of(new User()));

        UserDetails returnedUser = userService.loadUserByUsername("firstname");

        assertNotNull(returnedUser);
    }

    @Test
    void loadUserByUsernameThrowsExceptionWhenUserNotFound() {
        when(userRepository.findByUsername("firstname")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("firstname"));
    }


    @Test
    void createCreatesNewUser() {
        UserResponse expected = createDummyUserResponse();
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        User user = createDummyUser();
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(userMapper.createRequestToUser(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.userToUserResponse(any())).thenReturn(expected);

        UserResponse actual = userService.create(userCreateRequest);

        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

    @Test
    void createThrowsUsernameAlreadyExistsException() {
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(createDummyUser()));

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.create(userCreateRequest));
    }
}
