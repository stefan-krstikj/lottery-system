package com.stefankrstikj.lotterysystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
