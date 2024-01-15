package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ReservationDto {
    private int id;
    private String startDate;
    private String endDate;
    private int price;
    private int numberOfPersons;
    private String comments;
    private int customerId;
    private int cottageId;
    private String status;
}

