package com.marketplace.adapters.in.web.category;
import jakarta.validation.constraints.*;
public record CategoryRequest(@NotBlank @Size(max=120) String name,@Size(max=1000) String description,Boolean active){}
