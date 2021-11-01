package com.karkowski.dddexample.warehouse.controller.cqrs;

import com.karkowski.dddexample.warehouse.controller.cqrs.command.Command;
import com.karkowski.dddexample.warehouse.controller.cqrs.command.CommandHandler;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.Query;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventBus implements CqrsBus {

    private final HandlerRegistry handlerRegistry;

    @Override
    public <R, C extends Command<R>> R executeCommand(final C command) {
        final CommandHandler<R,C> commandHandler = handlerRegistry.getCmd(command.getClass());
        return commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(final Q query) {
        final QueryHandler<R, Q> commandHandler = handlerRegistry.getQuery(query.getClass());
        return commandHandler.handle(query);
    }
}
