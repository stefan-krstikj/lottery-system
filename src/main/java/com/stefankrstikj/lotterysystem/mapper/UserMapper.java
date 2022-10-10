package com.stefankrstikj.lotterysystem.mapper;

import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User createRequestToUser(UserCreateRequest userCreateRequest) {
        return new User(userCreateRequest.getUsername(), userCreateRequest.getPassword(),
                userCreateRequest.getFirstName(), userCreateRequest.getLastName());
    }

    public UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
    }
}
