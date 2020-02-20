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

    @Enumerated(EnumType.STRING)
    private Status status;

    private String statusReason;

    public Transfer() {

    }

    private Transfer(final Builder builder) {
        setId(builder.id);
        setOriginAccount(builder.originAccount);
        setDestinationAccoutn(builder.destinationAccoutn);
        setAmount(builder.amount);
        setStatus(builder.status);
        setStatusReason(builder.statusReason);
    }

    public static Builder aTransfer() {
        return new Builder();
    }

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

    public static final class Builder {
        private long id;
        private Account originAccount;
        private Account destinationAccoutn;
        private BigDecimal amount;
        private Status status;
        private String statusReason;

        private Builder() {
        }

        public Builder withId(final long val) {
            id = val;
            return this;
        }

        public Builder withOriginAccount(final Account val) {
            originAccount = val;
            return this;
        }

        public Builder withDestinationAccoutn(final Account val) {
            destinationAccoutn = val;
            return this;
        }

        public Builder withAmount(final BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder withStatus(final Status val) {
            status = val;
            return this;
        }

        public Builder withStatusReason(final String val) {
            statusReason = val;
            return this;
        }

        public Transfer build() {
            return new Transfer(this);
        }
    }
}
