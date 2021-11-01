package com.karkowski.dddexample.warehouse.controller.cqrs.query.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.QueryHandler;
import com.karkowski.dddexample.warehouse.service.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetAllProductsQueryHandler implements QueryHandler<Flux<ProductDTO>, GetAllProductsQuery> {

    private final Warehouse warehouse;

    @Override
    public Flux<ProductDTO> handle(GetAllProductsQuery query) {
        return warehouse.getProducts().map(ProductDTO::new);
    }
}
