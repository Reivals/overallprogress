package com.karkowski.dddexample.warehouse.domain.event;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class ProductCreatedEvent extends Event {
    public static final String EVENT_NAME = "product-created";
    private final String productCode;

    @Override
    public String getEvent() {
        return toString();
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }
}
