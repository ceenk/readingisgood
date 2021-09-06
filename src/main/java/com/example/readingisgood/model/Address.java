package com.example.readingisgood.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Address {

    @NotBlank(message = "country must not be blank")
    private final String country;

    @NotBlank(message = "city must not be blank")
    private final String city;

    @NotBlank(message = "details must not be blank")
    private final String details;
}
