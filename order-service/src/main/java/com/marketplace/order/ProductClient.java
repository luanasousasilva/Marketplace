package com.marketplace.order;
import org.springframework.cloud.openfeign.FeignClient; import org.springframework.web.bind.annotation.*;
@FeignClient(name="product-service",url="${clients.product-service.url}") interface ProductClient { @PostMapping("/api/products/{id}/reservations") void reserve(@PathVariable Long id,@RequestBody ReservationRequest request,@RequestHeader("Idempotency-Key") String key); record ReservationRequest(int quantity){} }
