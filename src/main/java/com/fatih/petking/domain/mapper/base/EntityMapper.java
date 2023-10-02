package com.fatih.petking.domain.mapper.base;

import com.fatih.petking.application.model.request.base.Request;
import com.fatih.petking.domain.entity.base.AbstractIdEntity;

import java.util.List;

public interface EntityMapper<R extends Request, D, E extends AbstractIdEntity> {

    D requestToDto(R request);

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}