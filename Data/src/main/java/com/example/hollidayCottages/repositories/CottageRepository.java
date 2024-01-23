package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage, Integer> {
    @Query("SELECT MAX(c.id) FROM Cottage c")
    int findLastCottageId();
    @Query("SELECT h FROM Cottage h WHERE h.maxPersons >= :maxPersons")
    List<Cottage> findHousesByNumberOfPerson(int maxPersons);
}