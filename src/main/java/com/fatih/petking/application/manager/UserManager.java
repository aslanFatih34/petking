package com.fatih.petking.application.manager;

import com.fatih.petking.application.model.request.UserLoginRequest;
import com.fatih.petking.application.model.request.UserRegisterRequest;
import com.fatih.petking.application.model.response.AuthenticationResponse;
import com.fatih.petking.domain.entity.AppUser;
import com.fatih.petking.domain.service.AppUserService;
import com.fatih.petking.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final AppUserService appUserService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserRegisterRequest request) {
        AppUser appUser = appUserService.save(request);
        return new AuthenticationResponse(jwtService.generateToken(appUser));
    }

    public AuthenticationResponse login(UserLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
        return new AuthenticationResponse(jwtService.generateToken(userDetails));
    }
}
