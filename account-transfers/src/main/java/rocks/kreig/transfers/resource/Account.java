package rocks.kreig.transfers.resource;

public class Account {

    private final String id;
    private final String name;

    public Account(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
