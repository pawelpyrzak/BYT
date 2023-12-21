package com.example.hollidayCottages.infrastructure.dto.response.reservation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Getter
public class ReservationResponse {
    int reservationId;
    Date start_date;
    Date end_date;
    int price;
    int number_of_persons;
    int customer_id;
    int cottage_id;
    boolean status;
}
