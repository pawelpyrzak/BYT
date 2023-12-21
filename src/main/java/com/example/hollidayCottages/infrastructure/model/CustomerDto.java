package com.example.hollidayCottages.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private int customerId;
    private String name;
    private String surname;
    private char phone_number;
    private boolean regular_customer;
    private int user_id;

}
