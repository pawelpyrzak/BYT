package com.example.hollidayCottages.infrastructure.dto.request.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class UpdateReservationRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfPersons;
    private Long customerId;
    private Long cottageId;

    private boolean newStatus;

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }
}
