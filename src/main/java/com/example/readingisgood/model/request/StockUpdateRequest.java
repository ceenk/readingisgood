package com.example.readingisgood.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
public class StockUpdateRequest {

    @NotNull(message = "Id can not be null")
    private Long id;

    @NotNull(message = "Stock can not be null")
    @PositiveOrZero
    private Integer stock;
}
