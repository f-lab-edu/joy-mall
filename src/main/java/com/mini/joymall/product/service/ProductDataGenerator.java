package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductDataGenerator {

    private final JdbcTemplate jdbcTemplate;

    private static final String[] IPHONE_PRODUCTS = {
            "아이폰 실리콘 케이스", "아이폰 충전기", "아이폰 강화유리", "아이폰 보조배터리",
            "아이폰 무선충전기", "아이폰 이어폰", "아이폰 거치대", "아이폰 카메라 렌즈"
    };

    @Transactional
    public void generateProduct() {
        String sql = "INSERT INTO product (seller_id, name, description, image_url, created_date, updated_date) VALUES (?, ?, ?, ?, NOW(), NOW())";

        List<Product> products = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 2_000_000; i++) {
            String productName = IPHONE_PRODUCTS[random.nextInt(IPHONE_PRODUCTS.length)];
            products.add(new Product(
                    1L,
                    productName,
                    productName + "설명",
                    productName + "이미지"
            ));

            if (products.size() == 10000) {
                bathInsert(products, sql);
                products.clear();
            }
        }

        if (!products.isEmpty()) {
            bathInsert(products, sql);
        }
    }

    private void bathInsert(List<Product> products, String sql) {
        List<Object[]> batchArgs = products.stream()
                .map(product -> new Object[]{
                        product.getSellerId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImageUrl()
                })
                .toList();

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
