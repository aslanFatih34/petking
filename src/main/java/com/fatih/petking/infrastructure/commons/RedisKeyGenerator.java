package com.fatih.petking.infrastructure.commons;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class RedisKeyGenerator {

    public static final String API_PREFIX = "petking";
    public static final String KEY_SEPARATOR = ":";

    public static String generateKey(String... value) {
        StringBuilder builder = new StringBuilder(API_PREFIX);
        Arrays.asList(value).forEach(str -> builder.append(KEY_SEPARATOR).append(str));
        return builder.toString();
    }
}
