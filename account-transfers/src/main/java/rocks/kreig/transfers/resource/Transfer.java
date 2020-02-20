package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class Transfer {
    @JsonbProperty("id")
    private Long id;

    @JsonbProperty("origin")
    private Account origin;

    @JsonbProperty("destination")
    private Account destination;

    @JsonbProperty("amount")
    private BigDecimal amount;

    @JsonbProperty("status")
    private TransferStatus status;


    public Transfer(final Long id,
                    final Account origin,
                    final Account destination,
                    final BigDecimal amount,
                    final TransferStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.status = status;
    }

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public Account getOrigin() {
        return origin;
    }

    public Account getDestination() {
        return destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setOrigin(final Account origin) {
        this.origin = origin;
    }

    public void setDestination(final Account destination) {
        this.destination = destination;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(final TransferStatus status) {
        this.status = status;
    }
}
