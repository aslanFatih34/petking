package com.fatih.petking.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;

@Slf4j
public class CustomCacheErrorHandler extends SimpleCacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("Error occurred while getting value from the cache: {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("Error occurred while putting value to the cache : {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("Caught an error while evicting the cache : {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("Caught an error while clearing cache : {}.", cache.getName(), exception);
    }
}
