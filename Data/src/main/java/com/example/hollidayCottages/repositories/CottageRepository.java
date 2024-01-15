package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CottageRepository extends JpaRepository<Cottage, Integer> {
    @Query("SELECT MAX(c.id) FROM Cottage c")
    int findLastCottageId();
}