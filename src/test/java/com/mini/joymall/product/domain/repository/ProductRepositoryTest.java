package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    @Test
    @Transactional
    void 상품조회시_판매자정보를_가져온다() {
        // given
        Seller seller = new Seller(null, "a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());

        Product product = new Product("아이폰", "아이폰", 100.0, 100, "아이폰");
        seller.addProduct(product);
        sellerRepository.save(seller);

        // when
        List<Product> products = productRepository.findAllWithOrder();

        // then
        List<Seller> sellers = products.stream()
                .map(Product::getSeller)
                .toList();
        assertThat(products.size()).isEqualTo(sellers.size());
    }
}