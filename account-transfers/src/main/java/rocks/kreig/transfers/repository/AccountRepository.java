package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.repository.entity.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named
@ApplicationScoped
public class AccountRepository {

    @PersistenceContext(unitName = "transfer")
    private EntityManager entityManager;

    public Account findById(final long id) {
        final TypedQuery<Account> accountTypedQuery = entityManager.createNamedQuery("findById", Account.class);

        return accountTypedQuery.setParameter("id", id).getSingleResult();
    }
}
