package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.sale.domain.entity.SalesGroup;
import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.entity.SalesStatus;
import com.mini.joymall.sale.domain.repository.SalesGroupRepository;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SalesProductFacadeTest {
    @Autowired
    @Qualifier("salesProductFacadeRedisson")
    private SalesProductFacade salesProductFacade;

    @Autowired
    private SalesGroupRepository salesGroupRepository;

    @Autowired
    private SalesProductRepository salesProductRepository;

    @Test
    public void 판매_상품_동시성_재고_감소_테스트() throws InterruptedException {
        // given
        SalesProduct salesProduct = new SalesProduct(1L, 1000, 1000, SalesStatus.ON_SALES);

        Set<SalesProduct> salesProducts = new HashSet<>();
        salesProducts.add(salesProduct);
        SalesGroup salesGroup = new SalesGroup(salesProducts);
        SalesGroup savedSalesGroup = salesGroupRepository.save(salesGroup);
        List<SalesProduct> savedSalesProducts = savedSalesGroup.getSalesProducts().stream().toList();

        Long savedSalesProductId = savedSalesProducts.get(0).getId();

        OrderItem orderItem = new OrderItem(savedSalesProductId, 1, 1000);

        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);

        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(700);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
               try {
                   salesProductFacade.decreaseStock(orderItems);
               } finally {
                   latch.countDown();
               }
            });
        }
        latch.await();

        // then
        SalesProduct findSalesProduct = salesProductRepository.findById(savedSalesProductId).orElseThrow(NoSuchElementException::new);
        assertThat(findSalesProduct.getSalesStock()).isEqualTo(0);
    }
}