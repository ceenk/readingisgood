package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SecurityConstants;
import com.example.readingisgood.entity.OrderEntity;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.exception.InsufficientStockCountException;
import com.example.readingisgood.exception.OrderContentNotFoundException;
import com.example.readingisgood.exception.OrderNotFoundException;
import com.example.readingisgood.model.request.OrderBetweenDatesRequest;
import com.example.readingisgood.model.request.OrderRequest;
import com.example.readingisgood.model.response.OrderContentResponse;
import com.example.readingisgood.service.OrderContentService;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final JwtUtil jwtUtil;
    private final OrderService orderService;
    private final OrderContentService orderContentService;

    @PostMapping("/create")
    public ResponseEntity<OrderEntity> createNewOrder(@RequestHeader(name= SecurityConstants.AUTHORIZATION_HEADER_KEY) final String authorizationHeader,
                                                      @Valid @RequestBody final OrderRequest orderRequest)
            throws InsufficientStockCountException, EntityNotPersistedException {

        final String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        return ResponseEntity.ok(orderService.createNewOrder(username, orderRequest));
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderContentResponse> getOrderById(@RequestHeader (name=SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
                                                             @PathVariable("order_id") @Valid @Min(1) Long orderId) throws OrderNotFoundException, OrderContentNotFoundException {
        String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        return ResponseEntity.ok(
                OrderContentResponse.builder()
                        .username(username)
                        .orderEntity(orderService.getById(orderId))
                        .orderContentEntities(orderContentService.getOrderContentEntities(orderId))
                        .build()
        );
    }

    @GetMapping("/between")
    public ResponseEntity<List<OrderEntity>> getOrdersBetween(@RequestHeader (name=SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
                                                              @Valid @RequestBody final OrderBetweenDatesRequest betweenDatesRequest) {
        String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        return ResponseEntity.ok(
                orderService.getOrdersBetween(username, betweenDatesRequest.getStartDate(), betweenDatesRequest.getEndDate())
        );
    }
}
