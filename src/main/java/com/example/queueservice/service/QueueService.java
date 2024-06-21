package com.example.queueservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveZSetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class QueueService {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final ReactiveZSetOperations<String, String> zSetOps;

    @Autowired
    public QueueService(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOps = redisTemplate.opsForZSet();
    }

    public Mono<String> enterQueue(String userId) {
        // Add user to the queue with the current timestamp as the score
        return zSetOps.add("queue", userId, Instant.now().toEpochMilli())
                .map(result -> result ? "Entered queue" : "Failed to enter queue");
    }

    public Mono<String> exitQueue(String userId) {
        // Remove user from the queue
        return zSetOps.remove("queue", userId)
                .map(result -> result > 0 ? "Exited queue" : "Failed to exit queue");
    }

    public Mono<Long> getQueuePosition(String userId) {
        // Get the position of the user in the queue
        return zSetOps.rank("queue", userId)
                .map(rank -> rank != null ? rank + 1 : -1); // rank is 0-based, so add 1
    }

    public Mono<Long> getQueueSize() {
        // Get the size of the queue
        return zSetOps.size("queue");
    }
}
