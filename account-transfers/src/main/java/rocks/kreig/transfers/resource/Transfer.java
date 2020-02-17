package rocks.kreig.transfers.resource;

public class Transfer {
    private final long id;
    private final Account origin;
    private final Account destination;
    private final Money amount;

    private final TransferStatus status;

    public Transfer(final long id, final Account origin, final Account destination, final Money amount, final TransferStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.status = status;
    }

    public long getId() {
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
