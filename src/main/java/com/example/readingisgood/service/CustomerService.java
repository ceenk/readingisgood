package com.example.readingisgood.service;

import com.example.readingisgood.entity.CustomerEntity;
import com.example.readingisgood.exception.CustomerNotFoundException;

public interface CustomerService {

    CustomerEntity save(final CustomerEntity user);
    boolean customerExists(final String username);
    CustomerEntity getByUsername(final String username) throws CustomerNotFoundException;
}
