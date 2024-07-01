package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SalesProductService {

    private final SalesProductRepository salesProductRepository;

    @Transactional
    public void decreaseStock(OrderItem orderItem) {
        SalesProduct salesProduct = salesProductRepository.findById(orderItem.getSalesProductId())
                .orElseThrow(NoSuchElementException::new);
        salesProduct.decreaseStock(orderItem.getQuantity());
        salesProductRepository.save(salesProduct);
    }
}
