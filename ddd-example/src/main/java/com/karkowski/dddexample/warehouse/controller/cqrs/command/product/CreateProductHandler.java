package com.karkowski.dddexample.warehouse.controller.cqrs.command.product;

import com.karkowski.dddexample.warehouse.controller.cqrs.command.CommandHandler;
import com.karkowski.dddexample.warehouse.service.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateProductHandler implements CommandHandler<Mono<ProductCreatedResponse>, CreateProductCommand> {

    private final Warehouse warehouse;

    @Override
    public Mono<ProductCreatedResponse> handle(final CreateProductCommand command) {
        Mono.just(warehouse.addProduct(command.name(), command.code())
                        .thenReturn(new ProductCreatedResponse(command.code())));
        if(command == null) {
            throw new IllegalStateException("Command can't be null");
        }
        if(isCommandInvalid(command)) {
            throw new IllegalArgumentException("Wrong data in createProductCommand");
        }
        return warehouse.addProduct(command.name(), command.code())
                .thenReturn(new ProductCreatedResponse(command.code()));
    }


    private boolean isCommandInvalid(final CreateProductCommand command) {
        return command.code() == null || command.code().isBlank() ||
                command.name() == null || command.name().isBlank();
    }
}
