package com.marketplace.adapters.in.web.order;

import com.marketplace.application.order.OrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController @RequestMapping("/api/orders")
public class OrderController {
    private final OrderUseCase useCase;
    public OrderController(OrderUseCase useCase) { this.useCase = useCase; }
    @PostMapping public ResponseEntity<OrderUseCase.OrderView> create(@Valid @RequestBody CreateOrderRequest request) { var command = new OrderUseCase.CreateOrderCommand(request.customerName(), request.customerEmail(), request.items().stream().map(i -> new OrderUseCase.ItemCommand(i.productId(), i.quantity())).toList()); var order = useCase.create(command); return ResponseEntity.created(URI.create("/api/orders/" + order.id())).body(order); }
    @GetMapping("/{id}") public OrderUseCase.OrderView get(@PathVariable Long id) { return useCase.get(id); }
}
