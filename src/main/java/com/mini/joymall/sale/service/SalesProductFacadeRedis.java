package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component("salesProductFacadeRedis")
@RequiredArgsConstructor
public class SalesProductFacadeRedis implements SalesProductFacade {

    private final RedissonClient redissonClient;
    private final SalesProductService salesProductService;

    private static final String LOCK_KEY_PREFIX = "salesProduct:";

    @Override
    public void decreaseStock(Set<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            String lockKey = LOCK_KEY_PREFIX + orderItem.getSalesProductId();
            RLock lock = redissonClient.getLock(lockKey);

            try {
                boolean acquireLock = lock.tryLock(10, 1, TimeUnit.SECONDS);

                if (!acquireLock) {
                    throw new RuntimeException("SalesProduct Lock 획득 실패");
                }
                salesProductService.decreaseStock(orderItem);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Lock 획득 중 인터럽트 발생");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }
}
