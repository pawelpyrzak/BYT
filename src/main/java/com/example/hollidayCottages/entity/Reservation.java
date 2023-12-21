package com.example.hollidayCottages.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "RESERVATION_ID")
    private Long reservationId;
    @Column(name = "START_DATE")
    private LocalDate start_date;
    @Column(name = "END_DATE")
    private LocalDate end_date;
    @Column(name = "PRICE" )
    private int price;
    @Column(name = "NUMBER_OF_PERSONS")
    private int number_of_persons;
    @Column(name = "CUSTOMER_ID")
    private Long customer_id;
    @Column(name = "COTTAGE_ID")
    private Long cottage_id;
    @Column(name = "STATUS")
    private boolean status;
    }

