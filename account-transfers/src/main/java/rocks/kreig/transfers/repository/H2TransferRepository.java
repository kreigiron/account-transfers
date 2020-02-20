package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.repository.entity.Transfer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Optional;

@Named("h2TransferRepository")
@ApplicationScoped
public class H2TransferRepository implements TransferRepository {

    final DataSource dataSource;

    @Inject
    public H2TransferRepository(@Named("transferDataSource") final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Transfer> findById(final long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Transfer> create(final Transfer transfer) {
        return Optional.empty();
    }
}
