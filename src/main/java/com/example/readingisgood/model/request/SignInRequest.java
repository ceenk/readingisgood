package com.example.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInRequest {

    @NotBlank(message = "Username can not be blank")
    @Size(min = 4, max = 20, message = "Username length must be between 4 and 20 characters")
    private final String username;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max = 50, message = "Password length must be between 8 and 50 characters")
    private final String password;
}
