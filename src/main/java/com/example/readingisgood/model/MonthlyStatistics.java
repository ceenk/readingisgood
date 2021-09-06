package com.example.readingisgood.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MonthlyStatistics {

    private final BigDecimal totalPrice;
    private final Integer totalOrderCount;
    private final Integer totalBookCount;
}
