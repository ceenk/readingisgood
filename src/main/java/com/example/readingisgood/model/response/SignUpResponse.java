package com.example.readingisgood.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpResponse {

    private final String username;
    private final String message;
}
