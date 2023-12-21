package com.example.hollidayCottages.infrastructure.dto.request.reservation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ReservationRequest {
    private Date startDate;
    private Date endDate;
    private int numberOfPersons;
    private Long customerId;
    private Long cottageId;
}
