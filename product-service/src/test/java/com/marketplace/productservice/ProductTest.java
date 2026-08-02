package com.marketplace.productservice;
import org.junit.jupiter.api.Test; import java.math.BigDecimal; import static org.assertj.core.api.Assertions.*;
class ProductTest { @Test void rejectsReservationAboveAvailableStock(){var product=new Product("Mouse",new BigDecimal("99.90"),2);assertThatThrownBy(()->product.reserve(3)).isInstanceOf(InsufficientStockException.class);} @Test void reservesAvailableStock(){var product=new Product("Mouse",new BigDecimal("99.90"),2);product.reserve(2);assertThat(product.getStock()).isZero();} }
