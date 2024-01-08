package com.example.hollidayCottages.contract;

import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class CusRes {
    private Reservation reservation;
    private Customer customer;

}
