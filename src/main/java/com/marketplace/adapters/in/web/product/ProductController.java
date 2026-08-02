package com.marketplace.adapters.in.web.product;

import com.marketplace.application.product.ProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController @RequestMapping("/api/products")
public class ProductController {
    private final ProductUseCase useCase;
    public ProductController(ProductUseCase useCase) { this.useCase = useCase; }
    @GetMapping public ProductUseCase.PageResult<ProductUseCase.ProductView> list(@RequestParam(required = false) String q, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) { return useCase.list(q, page, size); }
    @GetMapping("/{id}") public ProductUseCase.ProductView get(@PathVariable Long id) { return useCase.get(id); }
    @PostMapping public ResponseEntity<ProductUseCase.ProductView> create(@Valid @RequestBody ProductRequest r) { var product = useCase.create(new ProductUseCase.CreateProductCommand(r.name(), r.description(), r.categoryId(), r.price(), r.stock())); return ResponseEntity.created(URI.create("/api/products/" + product.id())).body(product); }
    @PutMapping("/{id}") public ProductUseCase.ProductView update(@PathVariable Long id, @Valid @RequestBody ProductRequest r) { return useCase.update(id, new ProductUseCase.UpdateProductCommand(r.name(), r.description(), r.categoryId(), r.price(), r.stock(), r.active() == null || r.active())); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { useCase.deactivate(id); }
}
