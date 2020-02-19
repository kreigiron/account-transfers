package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class Account {

    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("number")
    private String number;

    @JsonbProperty("balance")
    private BigDecimal balance;

    public Account(final Long id,
                   final String name,
                   final String number,
                   final BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public Account() {
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }
}
