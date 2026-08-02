package com.marketplace.adapters.out.persistence.order;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity @Table(name = "orders")
class OrderJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(nullable = false, length = 120) String customerName;
    @Column(nullable = false, length = 254) String customerEmail;
    @Column(nullable = false, updatable = false) Instant createdAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) List<OrderItemJpaEntity> items = new ArrayList<>();
}
