package com.karkowski.dddexample.warehouse.controller.cqrs.query;

public interface QueryHandler<R, Q extends Query<R>> {
    R handle(Q query);
}
