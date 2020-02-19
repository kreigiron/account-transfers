package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;

public class Transfer {
    private final Long id;

    // TODO add javax validation constraints
    private final Account origin;
    private final Account destination;
    private final Money amount;

    private final TransferStatus status;

    @JsonbCreator
    public Transfer(@JsonbProperty("id") final Long id,
                    @JsonbProperty("origin") final Account origin,
                    @JsonbProperty("destination") final Account destination,
                    @JsonbProperty("amount") final Money amount,
                    @JsonbProperty("status") final TransferStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.status = status;
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

    public Money getAmount() {
        return amount;
    }

    public TransferStatus getStatus() {
        return status;
    }
}
