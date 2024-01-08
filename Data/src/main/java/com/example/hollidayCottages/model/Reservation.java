package com.example.hollidayCottages.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.sql.Date;


@Getter
@Setter
@Entity
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

    @JoinColumn(name = "Customer_id")
    private int customerId;

    @JoinColumn(name = "Cottage_id")
    private int cottageId;

    private String status;

    public Reservation(Date startDate, Date endDate, int price, int numberOfPersons, String comments, int customerId, int cottageId, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.numberOfPersons = numberOfPersons;
        this.comments = comments;
        this.customerId = customerId;
        this.cottageId = cottageId;
        this.status = status;
    }
}

