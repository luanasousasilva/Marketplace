package com.marketplace.config;

import com.marketplace.application.product.ProductUseCase;
import com.marketplace.application.category.CategoryUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;

@Configuration
class SampleDataConfig {
    @Bean CommandLineRunner sampleProducts(ProductUseCase products, CategoryUseCase categories) { return args -> {
        if (products.list(null, 0, 1).totalElements() == 0) {
            var electronics = categories.create(new CategoryUseCase.Command("Eletrônicos", "Tecnologia e acessórios", true));
            products.create(new ProductUseCase.CreateProductCommand("Fone Bluetooth", "Fone sem fio com cancelamento de ruído", electronics.id(), new BigDecimal("249.90"), 25));
            products.create(new ProductUseCase.CreateProductCommand("Teclado Mecânico", "Teclado compacto com iluminação RGB", electronics.id(), new BigDecimal("399.90"), 12));
            products.create(new ProductUseCase.CreateProductCommand("Suporte para notebook", "Suporte de alumínio ajustável", electronics.id(), new BigDecimal("89.90"), 40));
        }
    }; }
}
