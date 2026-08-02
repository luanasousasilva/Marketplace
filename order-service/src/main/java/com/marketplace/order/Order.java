package com.marketplace.order;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant;
@Entity @Table(name="orders") class Order { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) Long customerId; @Column(nullable=false,precision=19,scale=2) BigDecimal total; @Column(nullable=false) Instant createdAt=Instant.now(); protected Order(){} Order(Long c,BigDecimal t){customerId=c;total=t;} }
