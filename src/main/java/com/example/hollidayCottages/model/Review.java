package com.example.hollidayCottages.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    int id;
    String comment;
    int cottage_id;
    int customer_id;
    Date date;
    char aRate;
}
