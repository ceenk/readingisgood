package com.example.readingisgood.model.request;

import com.example.readingisgood.model.BookOrderModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "bookOrderModels can not be null")
    @NotEmpty(message = "bookOrderModels can not be empty")
    private List<BookOrderModel> bookOrderModels;
}
