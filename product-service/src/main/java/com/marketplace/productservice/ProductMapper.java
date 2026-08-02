package com.marketplace.productservice;
import org.mapstruct.*;
@Mapper(componentModel="spring") interface ProductMapper { @Mapping(target="available",expression="java(product.isAvailable())") ProductDtos.Response toResponse(Product product); }
