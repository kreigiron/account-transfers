package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class Account {

    private final Long id;
    private final String name;
    private final String number;
    private final BigDecimal balance;

    @JsonbCreator
    public Account(@JsonbProperty("id") final Long id,
                   @JsonbProperty("name")final String name,
                   @JsonbProperty("number")final String number,
                   @JsonbProperty("balance") final BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
