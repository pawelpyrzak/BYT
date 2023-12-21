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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private int price;

    private int numberOfPersons;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "Customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Cottage_id", nullable = false)
    private Cottage cottage;

    private String status;
}

