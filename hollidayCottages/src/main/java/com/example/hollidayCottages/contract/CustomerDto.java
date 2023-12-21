package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private boolean regularCustomer;

    private UserDto user;

}
