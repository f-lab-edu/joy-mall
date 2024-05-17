package com.mini.joymall.review.domain.repository;

import com.mini.joymall.review.domain.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
