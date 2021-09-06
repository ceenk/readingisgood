package com.example.readingisgood.model.response;

import com.example.readingisgood.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersResponse {

    private final String username;
    private final List<OrderEntity> orders;
}
