package com.jitesh.apiRateLimiter.RateLimiterAPI.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RateLimitService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final int MAX_REQUESTS_PER_MINUTE = 5;

    @Autowired
    public RateLimitService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String username) {
        String key = buildKey(username);

        Long currentCount = redisTemplate.opsForValue().increment(key);

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(1));
        }

        // Allow if count <= max allowed
        return currentCount <= MAX_REQUESTS_PER_MINUTE;
    }

    private String buildKey(String username) {
        String currentMinute = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        return "rate_limit:" + username + ":" + currentMinute;
    }

}
