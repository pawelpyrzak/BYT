package com.example.hollidayCottages.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int userId;
    private String email;
    private String password;
    private int userType_id;
}
