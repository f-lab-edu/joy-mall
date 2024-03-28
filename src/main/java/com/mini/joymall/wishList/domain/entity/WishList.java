package com.mini.joymall.wishList.domain.entity;

import com.mini.joymall.product.domain.entity.Product;
import lombok.extern.java.Log;

public class WishList {
    private Long id;
    private Product product;
    private Long customerId;
}
