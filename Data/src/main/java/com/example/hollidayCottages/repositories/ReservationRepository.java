package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.cottageId = :cottageId " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR (r.endDate BETWEEN :startDate AND :endDate)) " +
            "OR (:startDate BETWEEN r.startDate AND r.endDate) OR (:endDate BETWEEN r.startDate AND r.endDate)")
    List<Reservation> findOverlapReservations(int cottageId, Date startDate, Date endDate);

    List<Reservation> findAllByCustomerId(int customerId);
    List<Reservation> findByStartDateBetweenAndCottageId(Date startDate, Date endDate, int cottageId);

}
