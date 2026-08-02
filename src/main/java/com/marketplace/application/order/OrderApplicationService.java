package com.marketplace.application.order;

import com.marketplace.application.shared.NotFoundException;
import com.marketplace.domain.order.*;
import com.marketplace.domain.product.Product;
import com.marketplace.domain.product.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public class OrderApplicationService implements OrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public OrderApplicationService(OrderRepository orderRepository, ProductRepository productRepository) { this.orderRepository = orderRepository; this.productRepository = productRepository; }
    @Override public OrderView create(CreateOrderCommand command) {
        List<OrderItem> items = command.items().stream().map(item -> reserve(item.productId(), item.quantity())).toList();
        return view(orderRepository.save(Order.create(command.customerName(), command.customerEmail(), items)));
    }
    @Override public OrderView get(Long id) { return view(orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido", id))); }
    private OrderItem reserve(Long productId, int quantity) { Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Produto", productId)); product.reserve(quantity); productRepository.save(product); return new OrderItem(product.id(), product.name(), product.price(), quantity); }
    private static OrderView view(Order order) { return new OrderView(order.id(), order.customerName(), order.customerEmail(), order.total(), order.createdAt(), order.items().stream().map(i -> new ItemView(i.productId(), i.productName(), i.unitPrice(), i.quantity())).toList()); }
}
