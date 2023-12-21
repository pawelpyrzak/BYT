package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int id;
    private String email;
    private String password;
    private UserTypeDto userType;
}
