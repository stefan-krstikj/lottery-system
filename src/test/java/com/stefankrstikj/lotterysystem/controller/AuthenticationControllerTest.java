package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.model.request.LoginRequest;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;
import com.stefankrstikj.lotterysystem.service.AuthenticationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.stefankrstikj.lotterysystem.data.UserDummyData.createDummyUserCreateRequest;
import static com.stefankrstikj.lotterysystem.data.UserDummyData.createDummyUserResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationControllerTest {
    private AuthenticationController authenticationController;

    private final static String BEARER_STRING = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTY2NTUxOTM1OSwiZXh" +
            "wIjoxNjY1NjA1NzU5fQ.P5auhZT4NonMbC95w2YmYDjjqWRg6PYgRSG0tIRmKDxWqt5wsnWddPUxGvMmdSHoyserUswyfFlZXq7D7RPvMA";

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationController = new AuthenticationController(authenticationService);
    }

    @AfterEach
    void tearDown() {
        authenticationController = null;
    }

    @Test
    void register() {
        UserResponse userResponse = createDummyUserResponse();
        UserCreateRequest userCreateRequest = createDummyUserCreateRequest();
        when(authenticationService.register(userCreateRequest)).thenReturn(userResponse);

        UserResponse actual = authenticationController.register(userCreateRequest);

        assertEquals(userResponse.getUsername(), actual.getUsername());
        assertEquals(userResponse.getId(), actual.getId());
        assertEquals(userResponse.getFirstName(), actual.getFirstName());
        assertEquals(userResponse.getLastName(), actual.getLastName());
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        when(authenticationService.login(loginRequest)).thenReturn(BEARER_STRING);

        String actual = authenticationController.login(loginRequest);

        assertEquals(BEARER_STRING, actual);
    }
}
