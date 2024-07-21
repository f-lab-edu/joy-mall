package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("salesProductFacadeRedis")
@RequiredArgsConstructor
public class SalesProductFacadeRedis implements SalesProductFacade {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String STOCK_KEY_PREFIX = "salesProduct_stock:";
    private static final String CHANGE_LOG_KEY = "salesProduct_stock_change_log";

    @Override
    public void decreaseStock(Set<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            String stockKey = STOCK_KEY_PREFIX + orderItem.getSalesProductId();

            if (redisTemplate.opsForValue().get(stockKey) == null) {
                redisTemplate.opsForValue().set(stockKey, "1000000");
            }

            Long remainStock = redisTemplate.opsForValue().decrement(stockKey, orderItem.getQuantity());

            if (remainStock == null || remainStock < 0) {
                redisTemplate.opsForValue().increment(stockKey, orderItem.getQuantity());
                throw new RuntimeException("판매 수량이 부족합니다.");
            }

            redisTemplate.opsForSet().add(CHANGE_LOG_KEY, stockKey);
        }
    }
}
