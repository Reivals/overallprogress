package com.karkowski.dddexample.warehouse.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public final class ProductId {

    private final String id;

    public ProductId(@NonNull final String id) {
        if(id.isBlank()) {
            throw new IllegalArgumentException("Id cannot be empty. Id passed: " + id);
        }
        this.id = id;
    }

    public String value() {
        return id;
    }
}
