package com.marketplace.adapters.out.persistence.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "order_items")
class OrderItemJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id", nullable = false) OrderJpaEntity order;
    @Column(nullable = false) Long productId;
    @Column(nullable = false, length = 120) String productName;
    @Column(nullable = false, precision = 19, scale = 2) BigDecimal unitPrice;
    @Column(nullable = false) int quantity;
}
