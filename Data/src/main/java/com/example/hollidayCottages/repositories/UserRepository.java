package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    List<User> findUsersByIdBetween(int idStart, int idEnd);


    Optional<User> findByEmail(String email);
}
