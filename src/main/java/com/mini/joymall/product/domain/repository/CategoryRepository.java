package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    List<Category> findByDepth(@Param("depth") int depth);

    List<Category> findByParentId(@Param("parentId") Long parentId);
}
