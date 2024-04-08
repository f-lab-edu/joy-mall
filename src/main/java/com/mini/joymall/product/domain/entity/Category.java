package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@TypeAlias("CATEGORY")
@Getter
@ToString
@NoArgsConstructor
public class Category {
    @Id
    @Column("CATEGORY_ID")
    private Long id;
    private Long parentId;
    private Integer depth;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Category(Long parentId, Integer depth, String name) {
        this(null, parentId, depth, name, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Category(Long categoryId, Long parentId, Integer depth, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = categoryId;
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}