package rocks.kreig.transfers.resource;

import java.math.BigDecimal;

public class Money {
    final String currencyCode;
    final BigDecimal amount;

    public Money(final String currencyCode, final BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}
