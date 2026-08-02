package com.marketplace.productservice;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
final class ProductDtos { private ProductDtos(){} record Request(@NotBlank @Size(max=120) String name,@NotNull @Positive BigDecimal price,@PositiveOrZero int stock){} record ReservationRequest(@NotNull @Positive int quantity){} record Response(Long id,String name,BigDecimal price,int stock,boolean available){} }
