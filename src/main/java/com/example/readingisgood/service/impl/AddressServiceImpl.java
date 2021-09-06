package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.AddressEntity;
import com.example.readingisgood.repository.AddressRepository;
import com.example.readingisgood.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressEntity save(AddressEntity addressEntity) {
        return addressRepository.save(addressEntity);
    }
}
