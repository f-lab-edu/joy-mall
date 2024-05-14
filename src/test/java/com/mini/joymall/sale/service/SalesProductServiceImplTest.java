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
class SalesProductServiceImplTest {

    @Autowired
    private SalesProductServiceImpl salesProductServiceImpl;

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

    @Test
    void 비관적_락_멀티_쓰레드_테스트() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    salesProductServiceImpl.decreaseStock(orderItems);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        SalesProduct findSalesProduct = salesProductRepository.findById(savedSalesProductId).orElseThrow(NoSuchElementException::new);
        Assertions.assertThat(findSalesProduct.getSalesStock()).isEqualTo(0);
    }
}