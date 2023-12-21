package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PromotionDto {
    private int id;
    private int discountPercentage;
    private CottageDto cottage;
    private Date startDate;
    private Date endDate;
    private int status;
}

