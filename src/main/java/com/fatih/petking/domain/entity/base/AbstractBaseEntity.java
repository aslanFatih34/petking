package com.fatih.petking.domain.entity.base;

import com.fatih.petking.domain.entity.base.enumtype.Status;
import com.fatih.petking.infrastructure.commons.DateHelper;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class AbstractBaseEntity extends AbstractIdStatusEntity {

    @PrePersist
    public void prePersist() {
        this.setStatus(Status.ACTIVE);
        this.setCreatedDate(DateHelper.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastUpdatedDate(DateHelper.now());
    }
}
