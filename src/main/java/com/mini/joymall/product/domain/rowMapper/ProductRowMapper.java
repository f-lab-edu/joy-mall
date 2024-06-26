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
                .email(rs.getString("email"))
                .name(rs.getString("seller_name"))
                .storeName(rs.getString("store_name"))
                .phoneNumber(rs.getString("phone_number"))
                .build();

        Product product = Product.builder()
                .sellerId(rs.getLong("seller_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .imageUrl(rs.getString("image_url"))
                .createdDate(rs.getObject("created_date", LocalDateTime.class))
                .updatedDate(rs.getObject("updated_date", LocalDateTime.class))
                .build();
        return product;
    }
}
