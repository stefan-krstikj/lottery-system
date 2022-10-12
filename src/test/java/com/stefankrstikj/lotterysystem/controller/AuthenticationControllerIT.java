package com.stefankrstikj.lotterysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.service.AuthenticationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.stefankrstikj.lotterysystem.data.UserDummyData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is not a full integration test as we are using a Mock Service.
 * These tests confirm that the HTTP Binding works as expected.
 */
@WebMvcTest(controllers = AuthenticationController.class)
class AuthenticationControllerIT {

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
    }

    @Test
    void register() throws Exception {
        when(authenticationService.register(any())).thenReturn(createDummyUserResponse());

        mockMvc.perform(post("/api/authentication/register")
                        .content(objectMapper.writeValueAsString(createDummyUserCreateRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.lastName").exists())
                .andDo(print());
    }

    @Test
    void registerThrowsUsernameException() throws Exception {
        when(authenticationService.register(any())).thenThrow(new UsernameAlreadyExistsException("username"));

        mockMvc.perform(post("/api/authentication/register")
                        .content(objectMapper.writeValueAsString(createDummyUserCreateRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        when(authenticationService.login(any())).thenReturn(BEARER_STRING);

        mockMvc.perform(post("/api/authentication/login")
                        .content(objectMapper.writeValueAsString(createDummyLoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(print());
    }
}
