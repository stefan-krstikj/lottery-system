package com.stefankrstikj.lotterysystem.data;

import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.request.UserResponse;

public class UserDummyData {
    public static User createDummyUser() {
        User user = new User("username", "password", "firstname", "lastname");
        user.setId(0L);
        return user;
    }

    public static UserCreateRequest createDummyUserCreateRequest() {
        return new UserCreateRequest("username", "password",
                "firstname", "lastname");
    }

    public static UserResponse createDummyUserResponse() {
        return new UserResponse(0L, "username", "firstname", "lastname");
    }
}
