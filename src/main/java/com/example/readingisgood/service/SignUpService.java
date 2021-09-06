package com.example.readingisgood.service;

import com.example.readingisgood.exception.EntityAlreadyExistException;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.model.request.SignUpRequest;
import com.example.readingisgood.model.response.SignUpResponse;

public interface SignUpService {

    SignUpResponse signUp(final SignUpRequest signUpRequest) throws EntityAlreadyExistException, EntityNotPersistedException;
}
