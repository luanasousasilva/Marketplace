package com.marketplace.application.product;

import com.marketplace.domain.product.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.*;
import com.marketplace.domain.category.CategoryRepository;
import com.marketplace.domain.category.Category;
import static org.assertj.core.api.Assertions.*;

class ProductApplicationServiceTest {
    @Test void createsProductThroughRepositoryPort() {
        ProductRepository repository = new ProductRepository() {
            public Product save(Product product) { return Product.restore(1L, product.name(), product.description(), product.categoryId(), product.price(), product.stock(), product.active(), product.createdAt()); }
            public Optional<Product> findById(Long id) { return Optional.empty(); } public List<Product> findActive(String q, int o, int l) { return List.of(); } public long countActive(String q) { return 0; }
        };
        CategoryRepository categories = new CategoryRepository() { public Category save(Category c){return c;} public Optional<Category> findById(Long id){return Optional.of(Category.restore(1L,"A","",true,java.time.Instant.now()));} public List<Category> findActive(){return List.of();} };
        var service = new ProductApplicationService(repository, categories);
        var created = service.create(new ProductUseCase.CreateProductCommand("Mouse", "Sem fio", 1L, new BigDecimal("79.90"), 10));
        assertThat(created.id()).isEqualTo(1L);
        assertThat(created.name()).isEqualTo("Mouse");
    }
}
