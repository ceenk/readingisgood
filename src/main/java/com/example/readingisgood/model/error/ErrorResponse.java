package com.example.readingisgood.model.error;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class ErrorResponse {

    private int statusCode;
    private LocalDate timestamp;
    private String message;
    private String description;
}
