package com.shu.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryRateLimiter {

    private final Map<String, Counter> cache = new ConcurrentHashMap<>();

    public boolean allow(String key, int limit, int periodSeconds) {
        long now = System.currentTimeMillis();
        Counter c = cache.computeIfAbsent(key, k -> new Counter(0, now));

        synchronized (c) {
            if (now - c.startTime > periodSeconds * 1000L) {
                c.count = 1;
                c.startTime = now;
                return true;
            }
            if (c.count >= limit) {
                return false;
            }
            c.count++;
            return true;
        }
    }

    static class Counter {
        int count;
        long startTime;
        Counter(int count, long startTime) {
            this.count = count;
            this.startTime = startTime;
        }
    }
}