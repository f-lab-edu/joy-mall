package com.mini.joymall.review.domain.repository;

import com.mini.joymall.review.domain.entity.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
