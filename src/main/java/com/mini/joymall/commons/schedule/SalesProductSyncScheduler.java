package com.mini.joymall.commons.schedule;

import com.mini.joymall.sale.domain.entity.SalesProduct;
import com.mini.joymall.sale.domain.repository.SalesProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;

@Profile("prod")
@Component
@RequiredArgsConstructor
public class SalesProductSyncScheduler {

    private final SalesProductRepository salesProductRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final SchedulerLeader schedulerLeader;
    private static final String CHANGE_LOG_KEY = "salesProduct_stock_change_log";

    @Scheduled(fixedRate = 60000)
    public void syncStockToDB() {
        if (!schedulerLeader.isLeader()) {
            return;
        }

        Set<String> stockKeys = redisTemplate.opsForSet().members(CHANGE_LOG_KEY);
        if (stockKeys == null || stockKeys.isEmpty()) return;

        for (String stockKey : stockKeys) {
            if (stockKey.isEmpty()) {
                return;
            }

            Long salesProductId = Long.valueOf(stockKey.replace("salesProduct_stock:", ""));
            String stockInRedis = redisTemplate.opsForValue().get(stockKey);

            if (stockInRedis != null) {
                int stock = Integer.parseInt(stockInRedis);
                SalesProduct salesProduct = salesProductRepository.findById(salesProductId)
                        .orElseThrow(NoSuchElementException::new);

                if (salesProduct != null) {
                    salesProduct.decreaseStock(salesProduct.getSalesStock() - stock);
                    salesProductRepository.save(salesProduct);
                }
            }
        }
    }
}
