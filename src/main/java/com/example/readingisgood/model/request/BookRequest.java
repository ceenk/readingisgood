package com.example.readingisgood.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
public class BookRequest {

    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Author can not be blank")
    private String author;

    @NotNull(message = "Price can not be null")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Stock can not be null")
    @PositiveOrZero
    private Integer stock;
}
