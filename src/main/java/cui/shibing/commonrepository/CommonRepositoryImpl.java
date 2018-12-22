package cui.shibing.commonrepository;

import org.hibernate.loader.plan.spi.EntityFetch;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;


public class CommonRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    public CommonRepositoryImpl(Class<T> domainClass, EntityManagerFactory em) {
        super(domainClass, SharedEntityManagerCreator.createSharedEntityManager(em));
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

}
