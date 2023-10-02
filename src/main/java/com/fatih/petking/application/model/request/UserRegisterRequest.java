package com.fatih.petking.application.model.request;

import com.fatih.petking.application.model.request.base.Request;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegisterRequest extends Request {

  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private String password;
}