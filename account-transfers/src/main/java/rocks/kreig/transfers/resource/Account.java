package rocks.kreig.transfers.resource;

import java.math.BigDecimal;

public class Account {

    private final long id;
    private final String name;
    private final String number;
    private final BigDecimal balance;

    public Account(final long id, final String name, final String number, final BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public long getId() {
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
