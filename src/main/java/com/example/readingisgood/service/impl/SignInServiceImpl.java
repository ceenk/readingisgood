package com.example.readingisgood.service.impl;

import com.example.readingisgood.model.request.SignInRequest;
import com.example.readingisgood.model.response.SignInResponse;
import com.example.readingisgood.service.SignInService;
import com.example.readingisgood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final AuthenticationManager authenticationManager;
    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public SignInResponse signIn(final SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        final var userDetails = userDetailsService.loadUserByUsername(signInRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return SignInResponse
                .builder()
                .jwt(jwt)
                .build();
    }
}
