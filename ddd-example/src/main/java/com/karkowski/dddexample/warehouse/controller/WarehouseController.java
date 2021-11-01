package com.karkowski.dddexample.warehouse.controller;

import com.karkowski.dddexample.warehouse.controller.cqrs.CqrsBus;
import com.karkowski.dddexample.warehouse.controller.cqrs.command.product.CreateProductCommand;
import com.karkowski.dddexample.warehouse.controller.cqrs.command.product.ProductCreatedResponse;
import com.karkowski.dddexample.warehouse.controller.cqrs.dto.ProductDTO;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.product.GetAllProductsQuery;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.product.GetProductsQuery;
import com.karkowski.dddexample.warehouse.domain.Product;
import com.karkowski.dddexample.warehouse.service.Warehouse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final CqrsBus eventBus;

    @GetMapping("/stock/{productId}")
    private Flux<ProductDTO> product(@PathVariable @NonNull final String productId) {
        return eventBus.executeQuery(new GetProductsQuery(Collections.singletonList(productId)));
    }

    @GetMapping("/stock")
    private Flux<ProductDTO> products() {
        return eventBus.executeQuery(new GetAllProductsQuery());
    }

    @PostMapping("/stock")
    private Mono<ProductCreatedResponse> createProduct(@RequestBody @NonNull
                                                           final CreateProductCommand createProductCommand) {
        Mono<ProductCreatedResponse> productCreatedResponseMono = eventBus.executeCommand(createProductCommand);
        return productCreatedResponseMono;
    }
}
