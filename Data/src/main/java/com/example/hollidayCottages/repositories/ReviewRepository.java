package com.example.hollidayCottages.repositories;

import com.example.hollidayCottages.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
