package com.stefankrstikj.lotterysystem.controller;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.model.request.LoginRequest;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;
import com.stefankrstikj.lotterysystem.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserCreateRequest userCreateRequest) throws UsernameAlreadyExistsException {
        return authenticationService.register(userCreateRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
