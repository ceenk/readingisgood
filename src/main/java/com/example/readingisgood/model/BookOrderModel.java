package com.example.readingisgood.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookOrderModel {

    private final Long bookId;
    private final Integer count;
}
