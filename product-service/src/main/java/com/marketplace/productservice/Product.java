package com.marketplace.productservice;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="products") class Product { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; @Column(nullable=false,precision=19,scale=2) BigDecimal price; @Column(nullable=false) int stock; protected Product(){} Product(String n,BigDecimal p,int s){name=n;price=p;stock=s;} }
