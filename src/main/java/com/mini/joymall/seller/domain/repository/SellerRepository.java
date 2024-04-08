package com.mini.joymall.seller.domain.repository;

import com.mini.joymall.seller.domain.entity.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller, Long> {
    List<Seller> findAll();
}
