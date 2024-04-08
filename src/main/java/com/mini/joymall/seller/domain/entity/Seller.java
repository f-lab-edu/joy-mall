package com.mini.joymall.seller.domain.entity;

import com.mini.joymall.product.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table("SELLER")
@Getter
@NoArgsConstructor
@ToString
public class Seller {

    @Id
    @Column("SELLER_ID")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String storeName;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @MappedCollection(idColumn = "SELLER_ID")
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Seller(String email, String password, String name, String storeName, String phoneNumber) {
        this(null, email, password, name, storeName, phoneNumber, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Seller(Long id, String email, String password, String name, String storeName, String phoneNumber,
                  LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
