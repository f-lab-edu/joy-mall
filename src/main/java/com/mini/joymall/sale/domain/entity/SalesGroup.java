package com.mini.joymall.sale.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@Table("SALES_GROUP")
public class SalesGroup {
    @Id
    @Column("SALES_GROUP_ID")
    private Long id;

    @MappedCollection(idColumn = "SALES_GROUP_ID")
    private Set<SalesProduct> salesProducts;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public SalesGroup(Set<SalesProduct> salesProducts) {
        this(salesProducts, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public SalesGroup(Set<SalesProduct> salesProducts, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.salesProducts = salesProducts;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
