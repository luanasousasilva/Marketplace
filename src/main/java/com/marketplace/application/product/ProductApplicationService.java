package com.marketplace.application.product;

import com.marketplace.application.shared.NotFoundException;
import com.marketplace.domain.product.*;
import com.marketplace.domain.category.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/** Application service: orchestrates a use case and delegates rules to the aggregate. */
@Transactional
public class ProductApplicationService implements ProductUseCase {
    private final ProductRepository repository;
    private final CategoryRepository categories;
    public ProductApplicationService(ProductRepository repository, CategoryRepository categories) { this.repository = repository; this.categories=categories; }
    @Override public ProductView create(CreateProductCommand command) { category(command.categoryId()); return view(repository.save(Product.create(command.name(), command.description(), command.categoryId(), command.price(), command.stock()))); }
    @Override public ProductView update(Long id, UpdateProductCommand command) { category(command.categoryId()); Product product = product(id); product.update(command.name(), command.description(), command.categoryId(), command.price(), command.stock(), command.active()); return view(repository.save(product)); }
    @Override public ProductView get(Long id) { return view(product(id)); }
    @Override public PageResult<ProductView> list(String query, int page, int size) { int safePage = Math.max(page, 0), safeSize = Math.min(Math.max(size, 1), 100); List<ProductView> products = repository.findActive(query, safePage * safeSize, safeSize).stream().map(ProductApplicationService::view).toList(); return new PageResult<>(products, safePage, safeSize, repository.countActive(query)); }
    @Override public void deactivate(Long id) { Product product = product(id); product.deactivate(); repository.save(product); }
    private Product product(Long id) { return repository.findById(id).orElseThrow(() -> new NotFoundException("Produto", id)); }
    private void category(Long id) { var category=categories.findById(id).orElseThrow(()->new NotFoundException("Categoria",id)); if(!category.active()) throw new IllegalArgumentException("Categoria inativa"); }
    private static ProductView view(Product p) { return new ProductView(p.id(), p.name(), p.description(), p.categoryId(), p.price(), p.stock(), p.active(), p.createdAt()); }
}
