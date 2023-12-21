package com.example.hollidayCottages.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    @Column(name = "REVIEW_ID")
    private int reviewId;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "COTTAGE_ID")
    private int cottage_id;
    @Column(name = "CUSTOMER_ID")
    private int customer_id;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "RATE")
    private double rate;
}
