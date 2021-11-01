package com.karkowski.dddexample.warehouse.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Data
public class Product {

    private final ProductId productId;
    private final String name;
    private final String code;
}
