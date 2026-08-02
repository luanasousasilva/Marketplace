package com.marketplace.domain.product;

import com.marketplace.domain.shared.DomainException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test void preventsStockGoingNegative() {
        Product product = Product.create("Cabo", "USB-C", 1L, new BigDecimal("20.00"), 2);
        assertThatThrownBy(() -> product.reserve(3)).isInstanceOf(DomainException.class);
    }
}
