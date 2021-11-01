package com.karkowski.dddexample.warehouse.controller.cqrs;

import com.karkowski.dddexample.warehouse.controller.cqrs.command.Command;
import com.karkowski.dddexample.warehouse.controller.cqrs.command.CommandHandler;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.Query;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerRegistry {

    private final Map<Class<? extends Command>, Class<? extends CommandHandler>> commandProviderMap = new HashMap<>();
    private final Map<Class<? extends Query>,  Class<? extends QueryHandler>> queryProviderMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public HandlerRegistry(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        Arrays.stream(applicationContext.getBeanNamesForType(CommandHandler.class))
                .forEach(handlerName -> registerCommand(applicationContext, handlerName));
        Arrays.stream(applicationContext.getBeanNamesForType(QueryHandler.class))
                .forEach(handlerName -> registerQuery(applicationContext, handlerName));
    }

    private void registerCommand(final ApplicationContext applicationContext, final String name ){
        Class<CommandHandler<?,?>> handlerClass = (Class<CommandHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandProviderMap.put(commandType, handlerClass);
    }

    private void registerQuery(ApplicationContext applicationContext, String name ){
        Class<QueryHandler<?,?>> handlerClass = (Class<QueryHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> queryType = (Class<? extends Query>) generics[1];
        queryProviderMap.put(queryType, handlerClass);
    }

    @SuppressWarnings("unchecked")
    <R, C extends Command<R>> CommandHandler<R,C> getCmd(final Class<C> commandClass) {
        return applicationContext.getBean(commandProviderMap.get(commandClass));
    }

    @SuppressWarnings("unchecked")
    <R, C extends Query<R>> QueryHandler<R,C> getQuery(final Class<C> queryClass) {
        return applicationContext.getBean(queryProviderMap.get(queryClass));
    }
}
