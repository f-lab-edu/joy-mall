package com.mini.joymall.commons.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SchedulerLeader {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String LEADER_KEY = "scheduler:leader";
    private static final long LEADER_TIMEOUT = 60000; // 60 seconds
    private String instanceId = UUID.randomUUID().toString();

    public boolean isLeader() {
        Boolean isLeader = redisTemplate.opsForValue().setIfAbsent(LEADER_KEY, instanceId, LEADER_TIMEOUT, TimeUnit.MILLISECONDS);

        if (Boolean.TRUE.equals(isLeader) || instanceId.equals(redisTemplate.opsForValue().get(LEADER_KEY))) {
            redisTemplate.expire(LEADER_KEY, LEADER_TIMEOUT, TimeUnit.MILLISECONDS);
            return true;
        }

        return false;
    }
}
