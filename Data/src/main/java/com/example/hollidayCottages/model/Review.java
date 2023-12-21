package com.example.hollidayCottages.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int comment;

    @ManyToOne
    @JoinColumn(name = "Cottage_id")
    private Cottage cottage;

    @ManyToOne
    @JoinColumn(name = "Customer_id")
    private Customer customer;

    private Date date;

    private char rate;
}
