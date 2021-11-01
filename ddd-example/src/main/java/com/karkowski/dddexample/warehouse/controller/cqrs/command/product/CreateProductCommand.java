package com.karkowski.dddexample.warehouse.controller.cqrs.command.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.command.Command;
import reactor.core.publisher.Mono;

public record CreateProductCommand(String code, String name) implements Command<Mono<ProductCreatedResponse>> {
}
