package com.example.hollidayCottages.repository;

import com.example.hollidayCottages.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Customer, Long> {
}
