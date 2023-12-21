package com.example.hollidayCottages.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ReviewDto {
    private int reviewId;
    private String comment;
    private int cottage_id;
    private int customer_id;
    private Date date;
    private double rate;

}
