package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.repository.entity.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * Repository object for account entity operations
 */
@Named
@ApplicationScoped
public class AccountRepository extends Repository<Account> {

    @PersistenceContext(unitName = "transfer")
    private EntityManager entityManager;

    public Optional<Account> findByNumber(final String number) {
        final TypedQuery<Account> accountTypedQuery = entityManager.createNamedQuery("findByNumber", Account.class);

        return accountTypedQuery.setParameter("number", number).getResultStream().findFirst();
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
