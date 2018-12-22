package cui.shibing.commonrepository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class CommonRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CommonRepository<T,ID> {
    public CommonRepositoryImpl(Class<T> domainClass, EntityManagerFactory em) {
        super(domainClass, SharedEntityManagerCreator.createSharedEntityManager(em));
    }
}
