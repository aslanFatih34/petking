package com.fatih.petking.application.model.response;

import com.fatih.petking.infrastructure.commons.enumtype.ValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusType implements ValueEnum<String> {
    SUCCESS("success"),
    FAILURE("failure");

    private final String value;
}
