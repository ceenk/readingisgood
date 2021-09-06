package com.example.readingisgood.configuration.security;

import com.example.readingisgood.entity.CustomerEntity;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final CustomerEntity customerEntity;

        try {
            customerEntity = customerService.getByUsername(username);
        } catch (final CustomerNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        return new User(customerEntity.getUsername(), customerEntity.getPassword(), new ArrayList<>());
    }
}
