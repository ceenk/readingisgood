package com.example.readingisgood.repository;

import com.example.readingisgood.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUsername(final String username);
    List<OrderEntity> findByUsernameAndOrderDateBetween(final String username, final LocalDate startDate, final LocalDate endDate);
}
