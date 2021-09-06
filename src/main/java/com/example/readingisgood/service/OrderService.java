package com.example.readingisgood.service;

import com.example.readingisgood.entity.OrderEntity;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.exception.InsufficientStockCountException;
import com.example.readingisgood.exception.OrderNotFoundException;
import com.example.readingisgood.model.MonthlyStatistics;
import com.example.readingisgood.model.request.OrderRequest;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderEntity> listAllOrders(final String username) throws CustomerNotFoundException;
    OrderEntity createNewOrder(final String username, final OrderRequest orderRequest)
            throws InsufficientStockCountException, EntityNotPersistedException;
    OrderEntity getById(final Long orderId) throws OrderNotFoundException;
    List<OrderEntity> getOrdersBetween(final String username, final String startDate, final String endDate);
    Map<Month, MonthlyStatistics> listAllOrdersMonthly(final String username) throws CustomerNotFoundException;
}
