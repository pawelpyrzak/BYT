package com.example.hollidayCottages.repository;

import com.example.hollidayCottages.entity.Cottage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageRepository extends CrudRepository<Cottage, Long> {
}
