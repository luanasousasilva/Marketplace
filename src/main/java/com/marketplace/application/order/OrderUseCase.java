package com.marketplace.application.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface OrderUseCase {
    OrderView create(CreateOrderCommand command);
    OrderView get(Long id);
    record CreateOrderCommand(String customerName, String customerEmail, List<ItemCommand> items) { }
    record ItemCommand(Long productId, int quantity) { }
    record OrderView(Long id, String customerName, String customerEmail, BigDecimal total, Instant createdAt, List<ItemView> items) { }
    record ItemView(Long productId, String productName, BigDecimal unitPrice, int quantity) { }
}
