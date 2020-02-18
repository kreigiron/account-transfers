package rocks.kreig.transfers.repository.entity;

import rocks.kreig.transfers.resource.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Account originAccount;

    @OneToOne
    private Account destinationAccoutn;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String statusReason;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(final Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccoutn() {
        return destinationAccoutn;
    }

    public void setDestinationAccoutn(final Account destinationAccoutn) {
        this.destinationAccoutn = destinationAccoutn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(final String statusReason) {
        this.statusReason = statusReason;
    }
}
