package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageRepository extends JpaRepository<Cottage, Integer> {
}