package com.fatih.petking.application.manager;

import com.fatih.petking.application.model.request.DogAdoptionRequest;
import com.fatih.petking.application.model.response.DogAdoptionResponse;
import com.fatih.petking.application.model.response.base.Response;
import com.fatih.petking.application.model.response.mapper.BaseResponseMapper;
import com.fatih.petking.application.model.response.mapper.DogAdoptionResponseMapper;
import com.fatih.petking.domain.dto.DogAdoptionDto;
import com.fatih.petking.domain.mapper.base.DogAdoptionMapper;
import com.fatih.petking.domain.service.DogAdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogManager {

    private final DogAdoptionService dogAdoptionService;
    private final BaseResponseMapper baseResponseMapper;
    private final DogAdoptionResponseMapper dogAdoptionResponseMapper;
    private final DogAdoptionMapper dogAdoptionMapper;

    public Response createDogAdoptionAdvert(DogAdoptionRequest request){
        var dogAdoptionDto = dogAdoptionMapper.requestToDto(request);
        dogAdoptionService.createDogAdoptionAdvert(dogAdoptionDto);
        return baseResponseMapper.mapSuccessfulResponse();
    }

    public DogAdoptionResponse retrieveDogAdoptionAdvert(String ref){
        var dogAdoptionDto = dogAdoptionService.retrieveDogAdoptionAdvert(ref);
        return dogAdoptionResponseMapper.mapToResponse(dogAdoptionDto);
    }
}
