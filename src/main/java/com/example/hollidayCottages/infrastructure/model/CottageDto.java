package com.example.hollidayCottages.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CottageDto {
    private int cottageId;
    private String name;
    private double pricePerNight;
    private String description;
    private Integer maxPersons;

}
