package com.example.readingisgood.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponse {

    private final String title;
    private final String message;
}
