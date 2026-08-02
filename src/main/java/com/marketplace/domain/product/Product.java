package com.marketplace.domain.product;

import com.marketplace.domain.shared.DomainException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/** Aggregate root for the catalog bounded context. */
public class Product {
    private final Long id;
    private String name;
    private String description;
    private Long categoryId;
    private BigDecimal price;
    private int stock;
    private boolean active;
    private final Instant createdAt;

    private Product(Long id, String name, String description, Long categoryId, BigDecimal price, int stock, boolean active, Instant createdAt) {
        this.id = id; this.name = required(name, "Nome"); this.description = description;
        this.categoryId = Objects.requireNonNull(categoryId, "Categoria é obrigatória");
        this.price = validPrice(price); this.stock = validStock(stock); this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "Data de criação é obrigatória");
    }
    public static Product create(String name, String description, Long categoryId, BigDecimal price, int stock) { return new Product(null, name, description, categoryId, price, stock, true, Instant.now()); }
    public static Product restore(Long id, String name, String description, Long categoryId, BigDecimal price, int stock, boolean active, Instant createdAt) { return new Product(id, name, description, categoryId, price, stock, active, createdAt); }
    public void update(String name, String description, Long categoryId, BigDecimal price, int stock, boolean active) { this.name = required(name, "Nome"); this.description = description; this.categoryId=Objects.requireNonNull(categoryId, "Categoria é obrigatória"); this.price = validPrice(price); this.stock = validStock(stock); this.active = active; }
    public void reserve(int quantity) { if (!active) throw new DomainException("Produto inativo"); if (quantity <= 0 || stock < quantity) throw new DomainException("Estoque insuficiente para o produto " + id); stock -= quantity; }
    public void deactivate() { active = false; }
    private static String required(String value, String field) { if (value == null || value.isBlank()) throw new DomainException(field + " é obrigatório"); return value.trim(); }
    private static BigDecimal validPrice(BigDecimal value) { if (value == null || value.signum() <= 0) throw new DomainException("Preço deve ser maior que zero"); return value; }
    private static int validStock(int value) { if (value < 0) throw new DomainException("Estoque não pode ser negativo"); return value; }
    public Long id() { return id; } public String name() { return name; } public String description() { return description; } public Long categoryId(){return categoryId;} public BigDecimal price() { return price; } public int stock() { return stock; } public boolean active() { return active; } public Instant createdAt() { return createdAt; }
}
