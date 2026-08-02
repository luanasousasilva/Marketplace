package com.marketplace.adapters.in.web.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record CreateOrderRequest(@NotBlank @Size(max = 120) String customerName, @NotBlank @Email @Size(max = 254) String customerEmail, @NotEmpty List<@Valid ItemRequest> items) {
    public record ItemRequest(@NotNull @Positive Long productId, @Positive int quantity) { }
}
