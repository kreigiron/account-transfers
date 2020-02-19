package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class Money {
    @JsonbProperty("currency")
    private String currency;

    @JsonbProperty("amount")
    private BigDecimal amount;

    public Money() {
    }

    public Money( final String currency,
                  final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
