package com.marketplace.payment;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant;
@Entity @Table(name="payments") class Payment { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false,unique=true) Long orderId; @Column(nullable=false,precision=19,scale=2) BigDecimal amount; @Column(nullable=false) String status="PENDING"; @Column(nullable=false) Instant createdAt=Instant.now(); protected Payment(){} Payment(Long o,BigDecimal a){orderId=o;amount=a;} }
