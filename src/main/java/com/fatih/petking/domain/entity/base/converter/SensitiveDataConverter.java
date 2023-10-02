package com.fatih.petking.domain.entity.base.converter;

import com.fatih.petking.infrastructure.commons.crypto.CryptoUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SensitiveDataConverter implements AttributeConverter<String, String> {

    public String convertToDatabaseColumn(String value) {
        return CryptoUtils.encrypt(value);
    }

    public String convertToEntityAttribute(String sensitiveValue) {
        return CryptoUtils.decrypt(sensitiveValue);
    }
}
