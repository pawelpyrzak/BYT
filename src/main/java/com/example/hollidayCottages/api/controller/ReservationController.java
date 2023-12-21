package com.example.hollidayCottages.api.controller;

import com.example.hollidayCottages.infrastructure.dto.request.reservation.ReservationRequest;
import com.example.hollidayCottages.infrastructure.model.ReservationDto;
import com.example.hollidayCottages.repository.ReservationRepository;
import com.example.hollidayCottages.service.ReservationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(path = "/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequest request){

        return null;
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long reservationId) {

        return null;
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByCustomerId(@PathVariable Long customerId) {

        return null;
    }

}
