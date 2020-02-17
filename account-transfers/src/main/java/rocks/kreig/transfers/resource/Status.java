package rocks.kreig.transfers.resource;

public enum Status {

    INITIATED(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELLED(4),
    FAILED(-1);

    int code;

    Status(final int code) {
        this.code = code;
    }
}
