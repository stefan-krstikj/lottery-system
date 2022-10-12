package com.stefankrstikj.lotterysystem.data;

import com.stefankrstikj.lotterysystem.model.User;
import com.stefankrstikj.lotterysystem.model.request.LoginRequest;
import com.stefankrstikj.lotterysystem.model.request.UserCreateRequest;
import com.stefankrstikj.lotterysystem.model.response.UserResponse;

public class UserDummyData {
    public final static String BEARER_STRING = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTY2NTUxOTM1OSwiZXh" +
            "wIjoxNjY1NjA1NzU5fQ.P5auhZT4NonMbC95w2YmYDjjqWRg6PYgRSG0tIRmKDxWqt5wsnWddPUxGvMmdSHoyserUswyfFlZXq7D7RPvMA";

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

    public static LoginRequest createDummyLoginRequest() {
        return new LoginRequest("username", "password");
    }
}
