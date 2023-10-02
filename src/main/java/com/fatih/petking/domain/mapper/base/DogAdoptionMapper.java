package com.fatih.petking.domain.mapper.base;

import com.fatih.petking.application.model.request.DogAdoptionRequest;
import com.fatih.petking.domain.dto.DogAdoptionDto;
import com.fatih.petking.domain.entity.DogAdoption;
import com.fatih.petking.infrastructure.configuration.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = MapstructConfig.class, imports = {UUID.class})
public interface DogAdoptionMapper extends EntityMapper<DogAdoptionRequest, DogAdoptionDto, DogAdoption> {

    @Override
    @Mapping(target = "ref", expression = "java(UUID.randomUUID().toString())")
    DogAdoptionDto requestToDto(DogAdoptionRequest request);
}
