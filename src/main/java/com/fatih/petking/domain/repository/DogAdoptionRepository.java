package com.fatih.petking.domain.repository;

import com.fatih.petking.domain.entity.DogAdoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogAdoptionRepository extends JpaRepository<DogAdoption, Long> {

    Optional<DogAdoption> findByRef(String ref);
}
