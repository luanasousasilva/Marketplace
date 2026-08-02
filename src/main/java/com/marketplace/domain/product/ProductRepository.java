package com.marketplace.domain.product;

import java.util.List;
import java.util.Optional;

/** Output port: the domain/application layer depends on this abstraction, never on JPA. */
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findActive(String query, int offset, int limit);
    long countActive(String query);
}
