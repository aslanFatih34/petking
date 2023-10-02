package com.fatih.petking.domain.service;

import java.util.concurrent.TimeUnit;

public interface CacheService {

    void save(String key, Long limit, Long ttl, TimeUnit timeUnit);

    Long retrieve(String key);

    Long decrement(String key);

    boolean existsValidKey(String key);
}