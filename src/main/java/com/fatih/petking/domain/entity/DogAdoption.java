package com.fatih.petking.domain.entity;

import com.fatih.petking.domain.entity.base.AbstractBaseEntity;
import com.fatih.petking.domain.entity.base.enumtype.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dog_adoption")
public class DogAdoption extends AbstractBaseEntity {

    @Column(name = "ref", nullable = false, unique = true, length = 40)
    private String ref;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age", nullable = false, length = 50)
    private String age;

    @Column(name = "race", nullable = false, length = 100)
    private String race;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 6)
    private Gender gender;

    @Column(name = "description", nullable = false)
    private String description;
}
