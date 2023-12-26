package com.fatih.petking.domain.service;

import com.fatih.petking.domain.dto.DogAdoptionDto;
import com.fatih.petking.domain.exception.base.BusinessException;
import com.fatih.petking.domain.mapper.base.DogAdoptionMapper;
import com.fatih.petking.domain.repository.DogAdoptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogAdoptionService {

    private final DogAdoptionRepository dogAdoptionRepository;
    private final DogAdoptionMapper dogAdoptionMapper;

    public void createDogAdoptionAdvert(DogAdoptionDto dogAdoptionDto){
        var dogAdoption = dogAdoptionMapper.toEntity(dogAdoptionDto);
        dogAdoptionRepository.save(dogAdoption);
    }
    @Cacheable(cacheNames = "dogAdoption", key = "'ref:'+#ref")
    public DogAdoptionDto retrieveDogAdoptionAdvert(String ref){
        return dogAdoptionRepository.findByRef(ref)
                .map(dogAdoptionMapper::toDto)
                .orElseThrow(() -> new BusinessException("business.notFound.dopAdoption", ref));
    }
}
