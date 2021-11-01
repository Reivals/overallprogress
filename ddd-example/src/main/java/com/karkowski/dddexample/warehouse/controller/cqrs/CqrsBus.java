package com.karkowski.dddexample.warehouse.controller.cqrs;

import com.karkowski.dddexample.warehouse.controller.cqrs.command.Command;
import com.karkowski.dddexample.warehouse.controller.cqrs.query.Query;

public interface CqrsBus {
    <R, C extends Command<R>> R executeCommand(C command);
    <R, Q extends Query<R>> R executeQuery(Q query);
}
