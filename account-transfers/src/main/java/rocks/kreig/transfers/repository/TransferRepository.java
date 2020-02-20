package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.repository.entity.Transfer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Optional;

@Named("h2TransferRepository")
@ApplicationScoped
public class TransferRepository extends Repository<Transfer> {

    @Override
    protected Class<Transfer> getEntityClass() {
        return Transfer.class;
    }
}
