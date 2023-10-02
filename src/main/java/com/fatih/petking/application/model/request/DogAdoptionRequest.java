package com.fatih.petking.application.model.request;

import com.fatih.petking.application.model.request.base.Request;
import com.fatih.petking.domain.entity.base.enumtype.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DogAdoptionRequest extends Request {
    private String name;
    private String age;
    private String race;
    private Gender gender;
    private String description;
}
