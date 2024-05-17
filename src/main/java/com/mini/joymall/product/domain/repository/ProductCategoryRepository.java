package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
}
