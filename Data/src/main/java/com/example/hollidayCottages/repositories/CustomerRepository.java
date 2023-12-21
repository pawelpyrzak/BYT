package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}