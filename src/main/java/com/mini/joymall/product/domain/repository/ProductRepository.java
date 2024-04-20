package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.rowMapper.ProductRowMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query(value = "select p.*, s.SELLER_ID, s.EMAIL, s.PASSWORD, s.NAME as SELLER_NAME, s.STORE_NAME, s.PHONE_NUMBER " +
            "from product p left outer join seller s on p.seller_id = s.seller_id"
    ,rowMapperClass = ProductRowMapper.class)
    List<Product> findAllWithOrder();
}
