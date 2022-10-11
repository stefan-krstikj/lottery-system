package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.model.request.LoginRequest;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;
import com.stefankrstikj.lotterysystem.security.jwt.JwtTokenUtil;
import com.stefankrstikj.lotterysystem.service.AuthenticationService;
import com.stefankrstikj.lotterysystem.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationServiceImpl(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = userService.loadUserByUsername(loginRequest.getUsername());
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public UserResponse register(UserCreateRequest userCreateRequest) throws UsernameAlreadyExistsException {
        userCreateRequest.setPassword(this.passwordEncoder.encode(userCreateRequest.getPassword()));
        return userService.create(userCreateRequest);
    }
}
