package com.example.readingisgood.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInResponse {

    private final String jwt;
}
