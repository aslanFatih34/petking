package com.fatih.petking.infrastructure.exception;

public class CryptoDecryptException extends RuntimeException {

    public CryptoDecryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
