package com.example.hollidayCottages.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {
    private String firstname;
    private String lastname;
    private String password;
    private String rePassword;
    private String email;
    private String phone;
}
