package com.example.hollidayCottages.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COTTAGE")
public class Cottage {
    @Id
    @Column(name = "COTTAGE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cottageId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE_PER_NIGHT")
    private int price_per_nightAnInt;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "MAX_PERSONS")
    private int max_persons;
}
