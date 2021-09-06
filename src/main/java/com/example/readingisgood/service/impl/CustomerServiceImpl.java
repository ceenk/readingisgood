package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.CustomerEntity;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity save(final CustomerEntity user) {
        return customerRepository.saveAndFlush(user);
    }

    @Override
    public boolean customerExists(final String username) {
        return customerRepository.existsById(username);
    }

    @Override
    public CustomerEntity getByUsername(final String username) throws CustomerNotFoundException {
        final var customer = customerRepository.getById(username);

        if (customer == null) {
            throw new CustomerNotFoundException(
                    String.format("User cannot be found with username: %s", username));
        }

        return customer;
    }
}
