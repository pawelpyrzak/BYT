package com.example.hollidayCottages.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {
    private int reservationId;
    private LocalDate start_date;
    private LocalDate end_date;;
    private int price;
    private int number_of_persons;
    private Long customer_id;
    private Long cottage_id;
    private boolean status;


}
