package com.example.wiki_clickstream.utils;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class MyCacheService {

    private final CacheManager cacheManager;

    public MyCacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void flushAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}