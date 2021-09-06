package com.example.readingisgood.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderBetweenDatesRequest {

    private final String startDate;
    private final String endDate;
}
