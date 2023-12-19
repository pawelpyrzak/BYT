package com.example.hollidayCottages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    int id;
    String name;
    String surname;
    char phone_number;
    boolean regular_customer;
    int user_id;
}
