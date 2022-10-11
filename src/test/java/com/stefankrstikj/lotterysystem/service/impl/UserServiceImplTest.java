package com.stefankrstikj.lotterysystem.service.impl;

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
    void loadUserByUsername() {
        // given
        when(userRepository.findByUsername("firstname")).thenReturn(Optional.of(new User()));

        // when
        UserDetails returnedUser = userService.loadUserByUsername("firstname");

        // then
        assertNotNull(returnedUser);
    }

    @Test
    void loadUserByUsernameThrowsExceptionWhenUserNotFound() {
        // given
        when(userRepository.findByUsername("firstname")).thenReturn(Optional.empty());

        // then
        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("firstname"));
    }


    @Test
    void create() {
        // given
        UserResponse expected = createDummyUserResponse();
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        User user = createDummyUser();
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(userMapper.createRequestToUser(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.userToUserResponse(any())).thenReturn(expected);

        // when
        UserResponse actual = userService.create(userCreateRequest);

        // then
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }
}
