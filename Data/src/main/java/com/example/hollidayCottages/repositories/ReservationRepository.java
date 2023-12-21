package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
