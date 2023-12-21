package com.example.hollidayCottages.repository;

import com.example.hollidayCottages.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);

}
