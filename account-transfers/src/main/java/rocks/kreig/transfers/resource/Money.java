package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class Money {
    final String currency;
    final BigDecimal amount;

    @JsonbCreator
    public Money(@JsonbProperty("currency") final String currency,
                 @JsonbProperty("amount") final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
