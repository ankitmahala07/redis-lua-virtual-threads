package com.ankitmahala07.virtualthreadredis.service;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventCreationService {

    private final RedisTemplate<String, String> redis;

    public EventCreationService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    public void create(String id, String payload, long executeAt) {

        redis.executePipelined((RedisCallback<?>) connection -> {

            String key = "event:" + id;

            connection.hSet(
                    key.getBytes(),
                    "payload".getBytes(),
                    payload.getBytes()
            );

            connection.zAdd(
                    "events:scheduled".getBytes(),
                    executeAt,
                    id.getBytes()
            );

            return null;
        });
    }
}

