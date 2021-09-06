package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SecurityConstants;
import com.example.readingisgood.exception.EntityAlreadyExistException;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.model.request.SignInRequest;
import com.example.readingisgood.model.request.SignUpRequest;
import com.example.readingisgood.model.response.OrdersResponse;
import com.example.readingisgood.model.response.SignInResponse;
import com.example.readingisgood.model.response.SignUpResponse;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.service.SignInService;
import com.example.readingisgood.service.SignUpService;
import com.example.readingisgood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody final SignUpRequest signUpRequest)
            throws EntityAlreadyExistException, EntityNotPersistedException {

        return ResponseEntity.ok(signUpService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody final SignInRequest signInRequest) {
        return ResponseEntity.ok(signInService.signIn(signInRequest));
    }

    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> listAllOrders(@RequestHeader (name= SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader) throws CustomerNotFoundException {
        String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        return ResponseEntity.ok(
                OrdersResponse.builder()
                        .username(username)
                        .orders(orderService.listAllOrders(username))
                        .build()
        );
    }
}
