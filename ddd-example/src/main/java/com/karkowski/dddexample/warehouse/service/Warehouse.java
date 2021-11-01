package com.karkowski.dddexample.warehouse.service;

import com.karkowski.dddexample.warehouse.domain.ProductId;
import com.karkowski.dddexample.warehouse.domain.Product;
import com.karkowski.dddexample.warehouse.domain.event.ProductCreatedEvent;
import com.karkowski.dddexample.warehouse.infra.eventstore.producer.WarehouseEventProducer;
import com.karkowski.dddexample.warehouse.infra.model.ProductDb;
import com.karkowski.dddexample.warehouse.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class Warehouse {

    private final ProductRepository productRepository;
    private final WarehouseEventProducer warehouseEventProducer;

    @Transactional
    public Mono<Void> addProduct(final String name, final String code) {
        return productRepository.save(new ProductDb(name, code))
                .map( x -> new ProductCreatedEvent(x.getCode()))
                .doOnSuccess(warehouseEventProducer::createEvent)
                .then();
    }

    public Mono<Product> getProduct(final ProductId productId) {
        return productRepository.findById(productId.value())
                .map(ProductDb::toDomainModel);
    }

    public Flux<Product> getProducts() {
        return productRepository.findAll()
                .map(ProductDb::toDomainModel);
    }

    public Flux<Product> getProducts(final Collection<ProductId> productIds) {
        return productRepository.findAllById(productIds.stream().map(ProductId::value).collect(Collectors.toList()))
                .map(ProductDb::toDomainModel);
    }
}
