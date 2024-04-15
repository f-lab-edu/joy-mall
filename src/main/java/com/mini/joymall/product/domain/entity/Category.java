package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("CATEGORY")
@Getter
@ToString
public class Category {
    @Id
    @Column("CATEGORY_ID")
    private Long categoryId;
    private final Long parentId;
    private final Integer depth;
    private final String name;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    @Builder
    public Category(Long parentId, Integer depth, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}