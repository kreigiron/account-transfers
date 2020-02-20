package rocks.kreig.transfers.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class Repository<T> {
    @PersistenceContext(unitName = "transfer")
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    public Optional<T> findById(long id) {
        final TypedQuery<T> typedQuery = entityManager.createNamedQuery("findById", getEntityClass());
        return typedQuery.setParameter("id", id).getResultStream().findFirst();
    }

    @Transactional
    public Optional<T> create(final T entity) {
        return Optional.of(entityManager.merge(entity));
    }

}
