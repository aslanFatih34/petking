package com.fatih.petking.application.model.response.mapper;

import com.fatih.petking.application.model.response.DogAdoptionResponse;
import com.fatih.petking.domain.dto.DogAdoptionDto;
import com.fatih.petking.infrastructure.configuration.MapstructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface DogAdoptionResponseMapper {
    DogAdoptionResponse mapToResponse(DogAdoptionDto dogAdoptionDto);
}
