package com.mini.joymall.order.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.sale.domain.entity.SalesGroup;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.entity.SalesStatus;
import com.mini.joymall.sale.domain.repository.SalesGroupRepository;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OrderValidatorTest {

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private SalesGroupRepository salesGroupRepository;

    @Test
    void 주문금액이_0원_이하라면_실패() {
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem(1L, 5, 0);
        OrderItem orderItem2 = new OrderItem(1L, 5, 0);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_상품이_비어있다면_실패() {
        Set<OrderItem> orderItems = new HashSet<>();
        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 판매상태가_판매중이_아니라면_실패() {
        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(new SalesProduct(1L, 5000, 100, SalesStatus.SOLD_OUT));
        List<SalesProduct> savedSalesProducts = salesGroupRepository.save(new SalesGroup(salesProducts))
                .getSalesProducts().stream().toList();

        OrderItem orderItem = new OrderItem(savedSalesProducts.get(0).getId(), 5, 3000);

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 판매재고가_0이하면_실패() {
        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(new SalesProduct(1L, 5000, 0, SalesStatus.ON_SALES));
        List<SalesProduct> savedSalesProducts = salesGroupRepository.save(new SalesGroup(salesProducts))
                .getSalesProducts().stream().toList();

        OrderItem orderItem = new OrderItem(savedSalesProducts.get(0).getId(), 5, 3000);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);

        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문금액과_상품_금액이_다르다면_실패() {
        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(new SalesProduct(1L, 5000, 100, SalesStatus.ON_SALES));
        List<SalesProduct> savedSalesProducts = salesGroupRepository.save(new SalesGroup(salesProducts))
                .getSalesProducts().stream().toList();

        OrderItem orderItem = new OrderItem(savedSalesProducts.get(0).getId(), 5, 3000);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);

        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 저장되지_않은_판매상품이라면_실패() {
        OrderItem orderItem = new OrderItem(100L, 1000, 1000);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);
        assertThatThrownBy(() -> orderValidator.validate(orderItems))
                .isInstanceOf(NoSuchElementException.class);
    }
}