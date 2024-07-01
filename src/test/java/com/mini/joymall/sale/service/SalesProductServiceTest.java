package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.sale.domain.entity.SalesGroup;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.entity.SalesStatus;
import com.mini.joymall.sale.domain.repository.SalesGroupRepository;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class SalesProductServiceTest {

    @Autowired
    private SalesProductService salesProductServiceImpl;

    @Autowired
    private SalesGroupRepository salesGroupRepository;

    @Autowired
    private SalesProductRepository salesProductRepository;

    private Long savedSalesProductId;

    private Set<OrderItem> orderItems = new HashSet<>();

    @BeforeEach
    void setUp() {
        SalesProduct salesProduct = new SalesProduct(1L, 1000, 100, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProduct);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        savedSalesProductId = savedSalesProducts.get(0).getId();

        OrderItem orderItem = new OrderItem(savedSalesProductId, 1, 1000);
        orderItems.add(orderItem);
    }
}