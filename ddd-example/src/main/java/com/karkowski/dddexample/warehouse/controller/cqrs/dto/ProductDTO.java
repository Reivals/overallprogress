package com.karkowski.dddexample.warehouse.controller.cqrs.dto;

import com.karkowski.dddexample.warehouse.domain.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProductDTO {
    private final String id;
    private final String name;
    private final String code;

    public ProductDTO(final Product product) {
        this.id = product.getProductId().value();
        this.code = product.getCode();
        this.name = product.getName();
    }

}
