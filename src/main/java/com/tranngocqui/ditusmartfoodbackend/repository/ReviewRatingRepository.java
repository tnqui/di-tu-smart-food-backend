package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.ReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRatingRepository extends JpaRepository<ReviewRating, UUID> {
}
