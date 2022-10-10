package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @AfterEach
    void tearDown() {
        userMapper = null;
    }

    @Test
    void createRequestToUser() {
        // given
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        User expectedUser = createDummyUser();

        // when
        User actual = userMapper.createRequestToUser(userCreateRequest);

        // then
        assertEquals(expectedUser.getUsername(), actual.getUsername());
        assertEquals(expectedUser.getPassword(), actual.getPassword());
        assertEquals(expectedUser.getFirstName(), actual.getFirstName());
        assertEquals(expectedUser.getLastName(), actual.getLastName());
    }

    @Test
    void userToUserResponse() {
        // given
        UserResponse expectedUserResponse = createDummyUserResponse();
        User user = createDummyUser();

        // when
        UserResponse actual = userMapper.userToUserResponse(user);

        // then
        assertEquals(expectedUserResponse.getUsername(), actual.getUsername());
        assertEquals(expectedUserResponse.getId(), actual.getId());
        assertEquals(expectedUserResponse.getFirstName(), actual.getFirstName());
        assertEquals(expectedUserResponse.getLastName(), actual.getLastName());
    }

    private User createDummyUser() {
        User user = new User("username", "password", "firstname", "lastname");
        user.setId(0L);
        return user;
    }

    private UserCreateRequest createDummyUserCreateRequest() {
        return new UserCreateRequest("username", "password",
                "firstname", "lastname");
    }

    private UserResponse createDummyUserResponse() {
        return new UserResponse(0L, "username", "firstname", "lastname");
    }
}
