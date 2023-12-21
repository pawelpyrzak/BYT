package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationDto {
    private int id;
    private Date startDate;
    private Date endDate;
    private int price;
    private int numberOfPersons;
    private String comments;
    private CustomerDto customer;
    private CottageDto cottage;
    private String status;
}

