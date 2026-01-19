package com.ankitmahala07.virtualthreadredis.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LuaScriptRegistry {

    private final Map<String, DefaultRedisScript<List>> scripts =
            new HashMap<>();

    public LuaScriptRegistry() {
        load("claim", "redis/scripts/claim_event.lua");
        load("requeue", "redis/scripts/requeue_expired.lua");
    }

    private void load(String name, String path) {
        DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setScriptSource(
                new ResourceScriptSource(new ClassPathResource(path))
        );
        script.setResultType(List.class);
        scripts.put(name, script);
    }

    public DefaultRedisScript<List> get(String name) {
        return scripts.get(name);
    }
}
