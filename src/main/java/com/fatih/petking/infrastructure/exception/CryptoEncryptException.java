package com.fatih.petking.infrastructure.exception;

public class CryptoEncryptException extends RuntimeException {

    public CryptoEncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
