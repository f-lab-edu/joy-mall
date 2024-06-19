package com.mini.joymall.order.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final SalesProductRepository salesProductRepository;

    public void validate(Set<OrderItem> orderItems) {
        validateOrderItems(orderItems);

        for (OrderItem orderItem : orderItems) {
            validateSalesProduct(orderItem, getSalesProduct(orderItem));
        }
    }

    private void validateOrderItems(Set<OrderItem> orderItems) {
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("주문 상품이 비어있습니다.");
        }

        if (Order.calculateOrderTotalPrice(orderItems) <= 0) {
            throw new IllegalArgumentException("최소 주문 금액 0원을 넘겨서 주문해주세요.");
        }
    }

    private void validateSalesProduct(OrderItem orderItem, SalesProduct salesProduct) {
        if (orderItem.getPricePerItem() != salesProduct.getSalesPrice()) {
            throw new IllegalArgumentException("판매 금액이 변경되었습니다.");
        }

        if (salesProduct.getSalesStock() == 0) {
            throw new IllegalArgumentException("품절된 상품입니다.");
        }

        if (!salesProduct.isOnSale()) {
            throw new IllegalArgumentException("판매 중인 상품이 아닙니다.");
        }
    }

    private SalesProduct getSalesProduct(OrderItem orderItem) {
        return salesProductRepository.findById(orderItem.getSalesProductId()).orElseThrow(NoSuchElementException::new);
    }
}
