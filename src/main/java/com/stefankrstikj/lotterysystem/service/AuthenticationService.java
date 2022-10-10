package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.model.request.LoginRequest;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;

public interface AuthenticationService {
    String login(LoginRequest loginRequest);

    UserResponse register(UserCreateRequest userCreateRequest) throws UsernameAlreadyExistsException;
}
