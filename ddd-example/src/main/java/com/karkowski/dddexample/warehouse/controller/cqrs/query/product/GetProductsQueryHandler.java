package com.karkowski.dddexample.warehouse.controller.cqrs.query.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.QueryHandler;
import com.karkowski.dddexample.warehouse.domain.ProductId;
import com.karkowski.dddexample.warehouse.service.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetProductsQueryHandler implements QueryHandler<Flux<ProductDTO>, GetProductsQuery> {

    private final Warehouse warehouse;

    @Override
    public Flux<ProductDTO> handle(final GetProductsQuery query) {
        return warehouse.getProducts(query.productIds().stream().map(ProductId::new).collect(Collectors.toList()))
                .map(ProductDTO::new);
    }
}
