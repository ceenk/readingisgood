package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.OrderContentEntity;
import com.example.readingisgood.exception.OrderContentNotFoundException;
import com.example.readingisgood.repository.OrderContentRepository;
import com.example.readingisgood.service.OrderContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderContentServiceImpl implements OrderContentService {

    private final OrderContentRepository orderContentRepository;

    @Override
    public OrderContentEntity save(final OrderContentEntity orderContentEntity) {
        return orderContentRepository.saveAndFlush(orderContentEntity);
    }

    @Override
    public List<OrderContentEntity> getOrderContentEntities(Long orderId) throws OrderContentNotFoundException {
        final List<OrderContentEntity> orderContentEntities = orderContentRepository.findByOrderId(orderId);

        if(orderContentEntities.isEmpty())
            throw new OrderContentNotFoundException(
                    String.format("Could not find order contents for order id: %s", orderId)
            );

        return orderContentEntities;
    }
}
