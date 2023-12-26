package com.fatih.petking.application.model.response;

import com.fatih.petking.application.model.response.base.Response;
import com.fatih.petking.domain.entity.base.enumtype.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DogAdoptionResponse extends Response {
    private String ref;
    private String name;
    private String age;
    private String race;
    private Gender gender;
    private String description;
}
