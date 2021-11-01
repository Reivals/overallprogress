package com.karkowski.dddexample.warehouse.controller.cqrs.query.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.Query;
import reactor.core.publisher.Flux;

import java.util.Collection;

public record GetProductsQuery(Collection<String> productIds) implements Query<Flux<ProductDTO>> {
}
