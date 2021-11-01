package com.karkowski.dddexample.warehouse.controller.cqrs.command;

public interface CommandHandler<R, C extends Command<R>> {
    R handle(C command);
}
