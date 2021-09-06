package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.AddressEntity;
import com.example.readingisgood.entity.CustomerEntity;
import com.example.readingisgood.exception.EntityAlreadyExistException;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.model.request.SignUpRequest;
import com.example.readingisgood.model.response.SignUpResponse;
import com.example.readingisgood.service.AddressService;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final CustomerService customerService;
    private final AddressService addressService;

    @Override
    public SignUpResponse signUp(final SignUpRequest signUpRequest) throws EntityAlreadyExistException, EntityNotPersistedException {
        final String username = signUpRequest.getUsername();
        final boolean customerExists = customerService.customerExists(username);

        if (customerExists) {
            throw new EntityAlreadyExistException(
                    String.format("Username: %s already exists, try again with different username", username));
        }

        var customerEntity = CustomerEntity.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .phone(signUpRequest.getPhone())
                .build();

        customerEntity = customerService.save(customerEntity);

        if (customerEntity == null) {
            throw new EntityNotPersistedException(
                    String.format("Could not persist customer with username: %s", username));
        }

        final var address = signUpRequest.getAddress();
        final var addressEntity = AddressEntity.builder()
                .username(username)
                .country(address.getCountry())
                .city(address.getCity())
                .details(address.getDetails())
                .build();

        addressService.save(addressEntity);

        final var response = SignUpResponse.builder()
                .username(username)
                .message(String.format("Successfully signed up username: %s", username))
                .build();

        return response;
    }
}
