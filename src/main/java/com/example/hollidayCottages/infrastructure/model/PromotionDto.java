package com.example.hollidayCottages.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PromotionDto {
    private int promotionId;
    private int disscount_percentage;
    private int cottage_id;
    private Date start_date;
    private Date end_date;
    private Enum status;
}
