package com.marketplace.adapters.out.persistence.product;

import com.marketplace.domain.product.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class JpaProductRepositoryAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;
    public JpaProductRepositoryAdapter(SpringDataProductRepository repository) { this.repository = repository; }
    public Product save(Product product) { return toDomain(repository.save(toEntity(product))); }
    public Optional<Product> findById(Long id) { return repository.findById(id).map(JpaProductRepositoryAdapter::toDomain); }
    public List<Product> findActive(String query, int offset, int limit) { var page = PageRequest.of(offset / limit, limit); return (query == null || query.isBlank() ? repository.findByActiveTrue(page) : repository.findByActiveTrueAndNameContainingIgnoreCase(query.trim(), page)).map(JpaProductRepositoryAdapter::toDomain).toList(); }
    public long countActive(String query) { return query == null || query.isBlank() ? repository.countByActiveTrue() : repository.countByActiveTrueAndNameContainingIgnoreCase(query.trim()); }
    private static ProductJpaEntity toEntity(Product p) { ProductJpaEntity e = new ProductJpaEntity(); e.id = p.id(); e.name = p.name(); e.description = p.description(); e.categoryId=p.categoryId(); e.price = p.price(); e.stock = p.stock(); e.active = p.active(); e.createdAt = p.createdAt(); return e; }
    private static Product toDomain(ProductJpaEntity e) { return Product.restore(e.id, e.name, e.description, e.categoryId, e.price, e.stock, e.active, e.createdAt); }
}
