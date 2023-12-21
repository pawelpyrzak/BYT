package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDto {
    private int id;
    private int comment;
    private CottageDto cottage;
    private CustomerDto customer;
    private Date date;
    private char rate;
}
