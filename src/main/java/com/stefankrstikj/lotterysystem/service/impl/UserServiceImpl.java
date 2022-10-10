package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.exception.UsernameAlreadyExistsException;
import com.stefankrstikj.lotterysystem.mapper.UserMapper;
import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;
import com.stefankrstikj.lotterysystem.repository.UserRepository;
import com.stefankrstikj.lotterysystem.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) throws UsernameAlreadyExistsException {
        if (isUsernameTaken(userCreateRequest.getUsername()))
            throw new UsernameAlreadyExistsException(userCreateRequest.getUsername());
        User user = mapper.createRequestToUser(userCreateRequest);
        return mapper.UserToUserResponse(userRepository.save(user));
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
