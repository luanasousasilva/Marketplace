package com.marketplace.adapters.out.persistence.category;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="categories") class CategoryJpaEntity { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false,unique=true,length=120) String name; @Column(length=1000) String description; @Column(nullable=false) boolean active; @Column(nullable=false,updatable=false) Instant createdAt; }
