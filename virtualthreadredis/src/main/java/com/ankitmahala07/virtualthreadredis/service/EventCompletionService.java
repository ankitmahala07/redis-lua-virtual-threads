package com.ankitmahala07.virtualthreadredis.service;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventCompletionService {

    private final RedisTemplate<String, String> redis;

    public EventCompletionService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    public void complete(String id) {

        redis.executePipelined((RedisCallback<?>) connection -> {

            connection.zRem(
                    "events:processing".getBytes(),
                    id.getBytes()
            );

            connection.del(
                    ("event:" + id).getBytes()
            );

            return null;
        });
    }
}
