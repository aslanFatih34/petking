package com.fatih.petking.application.controller;

import com.fatih.petking.application.manager.UserManager;
import com.fatih.petking.application.model.request.UserLoginRequest;
import com.fatih.petking.application.model.request.UserRegisterRequest;
import com.fatih.petking.application.model.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserManager userManager;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        return ResponseEntity.ok(userManager.register(userRegisterRequest));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        return ResponseEntity.ok(userManager.login(userLoginRequest));
    }
}
