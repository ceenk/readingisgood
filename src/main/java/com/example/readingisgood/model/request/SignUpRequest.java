package com.example.readingisgood.model.request;

import com.example.readingisgood.model.Address;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank(message = "Username can not be blank")
    @Size(min = 4, max = 20, message = "Username size must be between 4 and 20 characters")
    private final String username;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max = 50, message = "Password size must be between 8 and 50 characters")
    private final String password;

    @NotBlank(message = "E-mail can not be blank")
    @Email(message = "E-mail must be valid")
    private String email;

    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotBlank(message = "Surname can not be blank")
    private String surname;

    @NotBlank(message = "Phone can not be blank")
    private String phone;

    @Valid
    @NotNull(message = "Address can not be null")
    private Address address;
}
