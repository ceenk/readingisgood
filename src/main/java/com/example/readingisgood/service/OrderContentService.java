package com.example.readingisgood.service;

import com.example.readingisgood.entity.OrderContentEntity;
import com.example.readingisgood.exception.OrderContentNotFoundException;

import java.util.List;

public interface OrderContentService {

    OrderContentEntity save(final OrderContentEntity orderContentEntity);
    List<OrderContentEntity> getOrderContentEntities(final Long orderId) throws OrderContentNotFoundException;
}
