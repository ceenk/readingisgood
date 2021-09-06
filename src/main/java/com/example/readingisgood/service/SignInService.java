package com.example.readingisgood.service;

import com.example.readingisgood.model.request.SignInRequest;
import com.example.readingisgood.model.response.SignInResponse;

import javax.validation.Valid;

public interface SignInService {

    SignInResponse signIn(@Valid final SignInRequest signInRequest);
}
