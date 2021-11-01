package com.karkowski.dddexample.warehouse.controller.cqrs.query.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.Query;
import reactor.core.publisher.Flux;

public record GetAllProductsQuery() implements Query<Flux<ProductDTO>> {}
