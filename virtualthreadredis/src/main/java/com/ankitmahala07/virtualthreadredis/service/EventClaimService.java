package com.ankitmahala07.virtualthreadredis.service;


import com.ankitmahala07.virtualthreadredis.config.LuaScriptRegistry;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventClaimService {

    private final RedisTemplate<String, String> redis;
    private final LuaScriptRegistry scripts;

    public EventClaimService(
            RedisTemplate<String, String> redis,
            LuaScriptRegistry scripts
    ) {
        this.redis = redis;
        this.scripts = scripts;
    }

    public List<String> claim(int limit, long visibilityMs) {

        return redis.execute(
                scripts.get("claim"),
                List.of("events:scheduled", "events:processing"),
                String.valueOf(System.currentTimeMillis()),
                String.valueOf(limit),
                String.valueOf(visibilityMs)
        );
    }
}

