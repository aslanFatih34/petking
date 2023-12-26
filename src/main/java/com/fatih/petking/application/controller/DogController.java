package com.fatih.petking.application.controller;

import com.fatih.petking.application.manager.DogManager;
import com.fatih.petking.application.model.request.DogAdoptionRequest;
import com.fatih.petking.application.model.response.DogAdoptionResponse;
import com.fatih.petking.application.model.response.base.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dog/")
@RequiredArgsConstructor
public class DogController {

    private final DogManager dogManager;

    @PostMapping()
    public Response createDogAdoption(@RequestBody @Valid DogAdoptionRequest request) {
        return dogManager.createDogAdoptionAdvert(request);
    }

    @GetMapping("{ref}")
    public DogAdoptionResponse retrieveDogAdoptionAdvert(@PathVariable String ref) {
        return dogManager.retrieveDogAdoptionAdvert(ref);
    }
}
