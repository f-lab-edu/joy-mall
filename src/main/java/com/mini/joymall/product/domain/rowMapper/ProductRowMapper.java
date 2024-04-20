package com.mini.joymall.product.domain.rowMapper;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.seller.domain.entity.Seller;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seller seller = Seller.builder()
                .id(rs.getLong("seller_id"))
                .email(rs.getString("email"))
                .name(rs.getString("seller_name"))
                .storeName(rs.getString("store_name"))
                .phoneNumber(rs.getString("phone_number"))
                .build();

        Product product = Product.builder()
                .id(rs.getLong("product_id"))
                .sellerId(rs.getLong("seller_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .stockQuantity(rs.getInt("stock_quantity"))
                .imageUrl(rs.getString("image_url"))
                .createdDate(rs.getObject("created_date", LocalDateTime.class))
                .updatedDate(rs.getObject("updated_date", LocalDateTime.class))
                .build();
        product.setSeller(seller);
        return product;
    }
}
