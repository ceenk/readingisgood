package com.example.readingisgood.repository;

import com.example.readingisgood.entity.OrderContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContentEntity, Long> {

    List<OrderContentEntity> findByOrderId(final Long orderId);
}
