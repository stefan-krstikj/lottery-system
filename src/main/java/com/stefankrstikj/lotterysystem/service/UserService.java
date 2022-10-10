package com.stefankrstikj.lotterysystem.service;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponse create(UserCreateRequest userCreateRequest) throws UsernameAlreadyExistsException;
}
