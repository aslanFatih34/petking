package com.fatih.petking.application.model.request;

import com.fatih.petking.application.model.request.base.Request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegisterRequest extends Request {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}