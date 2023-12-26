package com.fatih.petking.infrastructure.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;

@Slf4j
public class CustomCacheErrorHandler extends SimpleCacheErrorHandler {

    @Override
    public void handleCacheGetError(@NotNull RuntimeException exception, Cache cache, Object key) {
        log.error("Error occurred while getting value from the cache: {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCachePutError(@NotNull RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("Error occurred while putting value to the cache : {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCacheEvictError(@NotNull RuntimeException exception, Cache cache, Object key) {
        log.error("Caught an error while evicting the cache : {} with key: {}.", cache.getName(), key.toString(), exception);
    }

    @Override
    public void handleCacheClearError(@NotNull RuntimeException exception, Cache cache) {
        log.error("Caught an error while clearing cache : {}.", cache.getName(), exception);
    }
}
