package com.example.hollidayCottages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cottage {
    int id;
    String name;
    int price_per_nightAnInt;
    String description;
    int max_persons;
}
