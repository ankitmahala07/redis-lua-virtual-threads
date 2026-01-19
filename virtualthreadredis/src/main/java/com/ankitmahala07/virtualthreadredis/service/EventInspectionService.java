package com.ankitmahala07.virtualthreadredis.service;

import com.ankitmahala07.virtualthreadredis.model.ZSetEntry;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventInspectionService {

    private final RedisTemplate<String, String> redis;

    public EventInspectionService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    public List<ZSetEntry> getScheduledEvents() {
        return readZSet("events:scheduled");
    }

    public List<ZSetEntry> getProcessingEvents() {
        return readZSet("events:processing");
    }

    private List<ZSetEntry> readZSet(String key) {

        var entries = redis.opsForZSet()
                .rangeWithScores(key, 0, -1);

        if (entries == null) {
            return List.of();
        }

        return entries.stream()
                .map(e -> new ZSetEntry(
                        e.getValue(),
                        e.getScore().longValue()
                ) {})
                .collect(Collectors.toList());
    }
}
