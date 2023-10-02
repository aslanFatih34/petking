package com.fatih.petking.application.model.request;

import com.fatih.petking.application.model.request.base.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest extends Request {

    @NotBlank(message = "validation.required.email")
    private String email;

    @NotBlank(message = "validation.required.password")
    private String password;
}
