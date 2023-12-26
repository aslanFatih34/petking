package com.fatih.petking.application.model.request;

import com.fatih.petking.application.model.request.base.Request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginRequest extends Request {

    @NotBlank(message = "validation.required.email")
    private String email;

    @NotBlank(message = "validation.required.password")
    private String password;
}
