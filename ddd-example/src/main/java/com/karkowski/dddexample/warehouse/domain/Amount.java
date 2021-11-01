package com.karkowski.dddexample.warehouse.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Amount implements Comparable<Amount> {

    public static final Amount ZERO = new Amount(0);

    private final int amount;

    public Amount(final int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("You can't create amount with value lower than 0. Argument passed: " + amount);
        }
        this.amount = amount;
    }

    public Amount add(final Amount addend) {
        return new Amount(this.amount + addend.amount);
    }

    public Amount subtract(final Amount addend) {
        final int subtraction = this.amount - addend.amount;
        if(subtraction < 0) {
            throw new IllegalStateException("You can't create negative amount. Amount after subtraction: " + subtraction);
        }
        return new Amount(this.amount - addend.amount);
    }

    @Override
    public int compareTo(final Amount o) {
        if (amount > o.amount) {
            return 1;
        } else if (amount < o.amount) {
            return -1;
        }
        return 0;
    }
}
