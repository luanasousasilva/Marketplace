package com.marketplace.application.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface ProductUseCase {
    ProductView create(CreateProductCommand command);
    ProductView update(Long id, UpdateProductCommand command);
    ProductView get(Long id);
    PageResult<ProductView> list(String query, int page, int size);
    void deactivate(Long id);
    record CreateProductCommand(String name, String description, Long categoryId, BigDecimal price, int stock) { }
    record UpdateProductCommand(String name, String description, Long categoryId, BigDecimal price, int stock, boolean active) { }
    record ProductView(Long id, String name, String description, Long categoryId, BigDecimal price, int stock, boolean active, Instant createdAt) { }
    record PageResult<T>(List<T> content, int page, int size, long totalElements) { }
}
