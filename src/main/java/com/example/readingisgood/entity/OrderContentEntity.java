package com.example.readingisgood.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_content")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
