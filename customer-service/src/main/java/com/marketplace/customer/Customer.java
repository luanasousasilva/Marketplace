package com.marketplace.customer;
import jakarta.persistence.*;
@Entity @Table(name="customers") class Customer { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false) String name; @Column(nullable=false,unique=true) String email; @Column(nullable=false,unique=true) String document; protected Customer(){} Customer(String n,String e,String d){name=n;email=e;document=d;} }
