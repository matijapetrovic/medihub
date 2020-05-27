package org.medihub.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {
    BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }
}
