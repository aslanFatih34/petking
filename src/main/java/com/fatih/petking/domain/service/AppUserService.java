package com.fatih.petking.domain.service;

import com.fatih.petking.application.model.request.UserRegisterRequest;
import com.fatih.petking.domain.entity.AppUser;
import com.fatih.petking.domain.entity.base.enumtype.Role;
import com.fatih.petking.domain.entity.base.enumtype.Status;
import com.fatih.petking.domain.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public AppUser save(UserRegisterRequest userRegisterRequest) {
        AppUser user = AppUser.builder()
                .userName(userRegisterRequest.getName())
                .email(userRegisterRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()))
                .createdDate(new Date())
                .status(Status.ACTIVE)
                .role(Role.USER)
                .build();

        return appUserRepository.save(user);
    }
}
