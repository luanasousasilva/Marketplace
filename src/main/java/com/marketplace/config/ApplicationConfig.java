package com.marketplace.config;

import com.marketplace.application.order.*;
import com.marketplace.application.product.*;
import com.marketplace.domain.order.OrderRepository;
import com.marketplace.domain.product.ProductRepository;
import com.marketplace.application.category.*;
import com.marketplace.application.party.*;
import com.marketplace.domain.category.CategoryRepository;
import com.marketplace.domain.party.PartyRepository;
import org.springframework.context.annotation.*;

/** Composition root: Spring wiring stays at the edge of the application. */
@Configuration
public class ApplicationConfig {
    @Bean ProductUseCase productUseCase(ProductRepository repository, CategoryRepository categories) { return new ProductApplicationService(repository, categories); }
    @Bean OrderUseCase orderUseCase(OrderRepository orders, ProductRepository products) { return new OrderApplicationService(orders, products); }
    @Bean CategoryUseCase categoryUseCase(CategoryRepository repository) { return new CategoryApplicationService(repository); }
    @Bean PartyUseCase partyUseCase(PartyRepository repository) { return new PartyApplicationService(repository); }
}
