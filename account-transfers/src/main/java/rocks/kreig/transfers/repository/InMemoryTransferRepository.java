package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.resource.Transfer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Named
@ApplicationScoped
public class InMemoryTransferRepository implements TransferRepository {

    private ConcurrentMap<Long, Transfer> transferConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public Optional<Transfer> get(final long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Transfer> create(final Transfer transfer) {
        return Optional.empty();
    }
}
