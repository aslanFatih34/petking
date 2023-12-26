package com.fatih.petking.domain.exception.base;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException {

    private final String key;
    private final Serializable[] arguments;

    public BusinessException(String key, Serializable... arguments) {
        this.key = key;
        this.arguments = arguments;
    }
}
