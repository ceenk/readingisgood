package com.example.readingisgood.model.response;

import com.example.readingisgood.entity.OrderContentEntity;
import com.example.readingisgood.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderContentResponse {

    private final String username;
    private final OrderEntity orderEntity;
    private final List<OrderContentEntity> orderContentEntities;
}
