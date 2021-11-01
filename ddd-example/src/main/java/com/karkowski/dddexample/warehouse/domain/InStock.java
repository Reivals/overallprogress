package com.karkowski.dddexample.warehouse.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class InStock {

    private final Amount amount;

    public InStock(final Amount amount) {
        this.amount = amount;
    }

    public InStock add(final Amount addend) {
        return new InStock(this.amount.add(addend));
    }

    public InStock remove(final Amount addend) {
        return new InStock(this.amount.subtract(addend));
    }

    public boolean hasEnough(final Amount amount) {
        return this.amount.compareTo(amount) == 1;
    }

    public Amount needs(final Amount amount) {
        return hasEnough(amount) ? Amount.ZERO : amount.subtract(this.amount);
    }

    public boolean isSoldOut() {
        return amount.equals(Amount.ZERO);
    }
}
