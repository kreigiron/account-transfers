package rocks.kreig.transfers.resource;

public class TransferStatus {

    final Status status;
    final String reason;

    public TransferStatus(final Status status, final String reason) {
        this.status = status;
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
