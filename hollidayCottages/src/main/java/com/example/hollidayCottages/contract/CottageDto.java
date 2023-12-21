package com.example.hollidayCottages.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CottageDto {
    private int id;
    private String name;
    private int pricePerNight;
    private String description;
    private int maxPersons;
}
