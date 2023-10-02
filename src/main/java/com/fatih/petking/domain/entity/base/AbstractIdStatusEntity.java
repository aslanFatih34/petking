package com.fatih.petking.domain.entity.base;

import com.fatih.petking.domain.entity.base.converter.StatusConverter;
import com.fatih.petking.domain.entity.base.enumtype.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractIdStatusEntity extends AbstractIdEntity {

    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;
}
