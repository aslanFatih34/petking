package com.fatih.petking.domain.entity.base.converter;

import com.fatih.petking.domain.entity.base.enumtype.Status;
import com.fatih.petking.infrastructure.commons.enumtype.EnumUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Integer value) {
        return EnumUtils.getValueEnum(Status.class, value);
    }
}
