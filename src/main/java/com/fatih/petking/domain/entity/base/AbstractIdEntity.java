package com.fatih.petking.domain.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractIdEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "optimized-sequence", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "optimized-sequence",
            type = GenericSequenceIdGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_per_entity_suffix", value = "_id_seq"),
                    @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @Parameter(name = "optimizer", value = "pooled-lo"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "idate", nullable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "udate")
    private Date lastUpdatedDate;
}
