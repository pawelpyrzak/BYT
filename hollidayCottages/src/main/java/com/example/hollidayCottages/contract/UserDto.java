package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String firstname;
    private String lastname;
    private String password;
    private String rePassword;
    private String email;
    private String phone;
}
