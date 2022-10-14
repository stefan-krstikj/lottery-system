package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.stefankrstikj.lotterysystem.data.UserDummyData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        User expectedUser = createDummyUser();

        User actual = userMapper.createRequestToUser(userCreateRequest);

        assertEquals(expectedUser.getUsername(), actual.getUsername());
        assertEquals(expectedUser.getPassword(), actual.getPassword());
        assertEquals(expectedUser.getFirstName(), actual.getFirstName());
        assertEquals(expectedUser.getLastName(), actual.getLastName());
    }

    @Test
    void userToUserResponse() {
        UserResponse expectedUserResponse = createDummyUserResponse();
        User user = createDummyUser();

        UserResponse actual = userMapper.userToUserResponse(user);

        assertEquals(expectedUserResponse.getUsername(), actual.getUsername());
        assertEquals(expectedUserResponse.getId(), actual.getId());
        assertEquals(expectedUserResponse.getFirstName(), actual.getFirstName());
        assertEquals(expectedUserResponse.getLastName(), actual.getLastName());
    }
}
