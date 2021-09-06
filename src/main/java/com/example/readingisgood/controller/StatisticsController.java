package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SecurityConstants;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.model.MonthlyStatistics;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final JwtUtil jwtUtil;
    private final OrderService orderService;

    @GetMapping("/monthly")
    public ResponseEntity<Map<Month, MonthlyStatistics>> listAllOrdersMonthly(@RequestHeader(name= SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader) throws CustomerNotFoundException {
        String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        return ResponseEntity.ok(orderService.listAllOrdersMonthly(username));
    }
}
