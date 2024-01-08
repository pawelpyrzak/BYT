package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByUserId(int user_id);

    Optional<Customer> findByUserId(int id);
}