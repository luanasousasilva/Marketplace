package com.marketplace.adapters.out.persistence.order;

import com.marketplace.domain.order.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class JpaOrderRepositoryAdapter implements OrderRepository {
    private final SpringDataOrderRepository repository;
    public JpaOrderRepositoryAdapter(SpringDataOrderRepository repository) { this.repository = repository; }
    public Order save(Order order) { return toDomain(repository.save(toEntity(order))); }
    public Optional<Order> findById(Long id) { return repository.findById(id).map(JpaOrderRepositoryAdapter::toDomain); }
    private static OrderJpaEntity toEntity(Order order) { OrderJpaEntity e = new OrderJpaEntity(); e.id = order.id(); e.customerName = order.customerName(); e.customerEmail = order.customerEmail(); e.createdAt = order.createdAt(); for (OrderItem item : order.items()) { OrderItemJpaEntity row = new OrderItemJpaEntity(); row.order = e; row.productId = item.productId(); row.productName = item.productName(); row.unitPrice = item.unitPrice(); row.quantity = item.quantity(); e.items.add(row); } return e; }
    private static Order toDomain(OrderJpaEntity e) { return Order.restore(e.id, e.customerName, e.customerEmail, e.items.stream().map(i -> new OrderItem(i.productId, i.productName, i.unitPrice, i.quantity)).toList(), e.createdAt); }
}
