package com.marketplace.adapters.in.web.product;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(@NotBlank @Size(max = 120) String name, @Size(max = 2000) String description, @NotNull @Positive Long categoryId, @NotNull @DecimalMin("0.01") @Digits(integer = 17, fraction = 2) BigDecimal price, @PositiveOrZero int stock, Boolean active) { }
