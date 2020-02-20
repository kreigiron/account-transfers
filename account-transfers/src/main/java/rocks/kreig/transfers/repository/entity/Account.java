package rocks.kreig.transfers.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NamedQueries({
                      @NamedQuery(name = "findById", query = "SELECT a from Account a where a.id = :id"),
                      @NamedQuery(name = "findByNumber", query = "SELECT a from Account a where a.number = :number")
              })
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String number;

    private BigDecimal balance;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

}
