package com.marketplace.domain.order;

import com.marketplace.domain.shared.DomainException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/** Aggregate root that keeps an immutable commercial snapshot of purchased products. */
public class Order {
    private final Long id;
    private final String customerName;
    private final String customerEmail;
    private final List<OrderItem> items;
    private final Instant createdAt;
    private Order(Long id, String customerName, String customerEmail, List<OrderItem> items, Instant createdAt) { this.id = id; this.customerName = required(customerName); this.customerEmail = required(customerEmail); this.items = new ArrayList<>(items); if (items.isEmpty()) throw new DomainException("Pedido deve ter ao menos um item"); this.createdAt = createdAt; }
    public static Order create(String customerName, String customerEmail, List<OrderItem> items) { return new Order(null, customerName, customerEmail, items, Instant.now()); }
    public static Order restore(Long id, String customerName, String customerEmail, List<OrderItem> items, Instant createdAt) { return new Order(id, customerName, customerEmail, items, createdAt); }
    private static String required(String value) { if (value == null || value.isBlank()) throw new DomainException("Dados do cliente são obrigatórios"); return value.trim(); }
    public Long id() { return id; } public String customerName() { return customerName; } public String customerEmail() { return customerEmail; } public List<OrderItem> items() { return List.copyOf(items); } public Instant createdAt() { return createdAt; }
    public BigDecimal total() { return items.stream().map(OrderItem::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add); }
}
