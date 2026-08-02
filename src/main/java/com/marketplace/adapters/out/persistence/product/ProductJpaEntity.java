package com.marketplace.adapters.out.persistence.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity @Table(name = "products")
class ProductJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(nullable = false, length = 120) String name;
    @Column(length = 2000) String description;
    @Column(nullable = false) Long categoryId;
    @Column(nullable = false, precision = 19, scale = 2) BigDecimal price;
    @Column(nullable = false) int stock;
    @Column(nullable = false) boolean active;
    @Column(nullable = false, updatable = false) Instant createdAt;
}
