package com.example.hollidayCottages.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "PROMOTION")
public class Promotion {
    @Id
    @Column(name = "PROMOTION_ID")
    private int promotionId;
    @Column(name = "DISSCOUNT_PERCENTAGE")
    private int disscount_percentage;
    @Column(name = "COTTAGE_ID")
    private int cottage_id;
    @Column(name = "START_DATE")
    private Date start_date;
    @Column(name = "END_DATE")
    private Date end_date;
    @Column(name = "STATUS")
    private Enum status;
}
