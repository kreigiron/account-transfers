package rocks.kreig.transfers.resource;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
    final String currencyCode;
    final BigDecimal amount;

    public Money(final String currencyCode, final BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }
}
