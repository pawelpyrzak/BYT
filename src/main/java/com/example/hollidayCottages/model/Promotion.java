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
public class Promotion {
    int id;
    int disscount_percentage;
    int cottage_id;
    Date start_date;
    Date end_date;
    int status;
}
