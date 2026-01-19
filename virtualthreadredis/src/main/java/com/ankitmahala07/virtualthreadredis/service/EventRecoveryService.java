package com.ankitmahala07.virtualthreadredis.service;

import com.ankitmahala07.virtualthreadredis.config.LuaScriptRegistry;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecoveryService {

    private final RedisTemplate<String, String> redis;
    private final LuaScriptRegistry scripts;

    public EventRecoveryService(
            RedisTemplate<String, String> redis,
            LuaScriptRegistry scripts
    ) {
        this.redis = redis;
        this.scripts = scripts;
    }

    @Scheduled(fixedDelay = 5000)
    public void requeue() {

        redis.execute(
                scripts.get("requeue"),
                List.of("events:processing", "events:scheduled"),
                String.valueOf(System.currentTimeMillis()),
                "100"
        );
    }
}
