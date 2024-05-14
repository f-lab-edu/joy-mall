package com.mini.joymall.sale.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("SALES_PRODUCT")
public class SalesProduct {
    @Id
    @Column("SALES_PRODUCT_ID")
    private Long id;

    @Column("PRODUCT_OPTION_ID")
    private Long productOptionId;

    private int salesPrice;
    private int salesStock;
    private SalesStatus salesStatus;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public SalesProduct(Long productOptionId, int salesPrice, int salesStock, SalesStatus salesStatus) {
        this(productOptionId, salesPrice, salesStock, salesStatus, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public SalesProduct(Long productOptionId, int salesPrice, int salesStock, SalesStatus salesStatus, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.productOptionId = productOptionId;
        this.salesPrice = salesPrice;
        this.salesStock = salesStock;
        this.salesStatus = salesStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int decreaseStock(int selectedQuantity) {
        int nowStock = this.salesStock - selectedQuantity;

        if (nowStock < 0) {
            throw new IllegalArgumentException("판매 수량이 부족합니다.");
        }

        this.salesStock = nowStock;
        return salesStock;
    }
}
