package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
