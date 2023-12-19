package com.example.hollidayCottages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Reservation {
    int id;
    Date start_date;
    Date end_date;
    int price;
    int number_of_persons;
    int customer_id;
    int cottage_id;
    String status;
}
