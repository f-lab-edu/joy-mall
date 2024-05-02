package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessimisticLockProductOptionServiceTest {

    @Autowired
    private PessimisticLockProductOptionService pessimisticLockProductOptionService;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    private Long savedId;

    @BeforeEach
    void setUp() {
        ProductOption productOption = new ProductOption(1L, "딸기맛", 1000, 100);
        ProductOption savedProductOption = productOptionRepository.save(productOption);
        savedId = savedProductOption.getId();
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
                   pessimisticLockProductOptionService.decreaseStock(savedId, 1);
               } finally {
                    latch.countDown();
               }
            });
        }
        latch.await();

        // then
        ProductOption findProductOption = productOptionRepository.findById(savedId).orElse(null);
        assertThat(findProductOption.getStockQuantity()).isEqualTo(0);
    }
}