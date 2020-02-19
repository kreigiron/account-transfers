package rocks.kreig.transfers.resource;

import javax.json.bind.annotation.JsonbProperty;

public class TransferStatus {

    @JsonbProperty("status")
    private Status status;

    @JsonbProperty("reason")
    private String reason;

    public TransferStatus(final Status status, final String reason) {
        this.status = status;
        this.reason = reason;
    }

    public TransferStatus() {
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }
}
