package com.marketplace.domain.order;

import com.marketplace.domain.shared.DomainException;
import java.math.BigDecimal;

public record OrderItem(Long productId, String productName, BigDecimal unitPrice, int quantity) {
    public OrderItem { if (productId == null || productName == null || unitPrice == null || unitPrice.signum() <= 0 || quantity <= 0) throw new DomainException("Item de pedido inválido"); }
    public BigDecimal subtotal() { return unitPrice.multiply(BigDecimal.valueOf(quantity)); }
}
