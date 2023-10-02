package com.fatih.petking.domain.dto;

import com.fatih.petking.domain.entity.base.enumtype.Gender;

public record DogAdoptionDto(
        String ref,
        String name,
        String age,
        String race,
        Gender gender,
        String description) {
}
