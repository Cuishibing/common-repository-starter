package cui.shibing.commonrepository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Deprecated
public class CommonRepositoryImpl1<T, ID> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    public CommonRepositoryImpl1(Class<T> domainClass, EntityManagerFactory em) {
        super(domainClass, SharedEntityManagerCreator.createSharedEntityManager(em));
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

}
