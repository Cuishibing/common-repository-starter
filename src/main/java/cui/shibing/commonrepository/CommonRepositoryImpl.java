package cui.shibing.commonrepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class CommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> domainClass;
    private SimpleJpaRepository<T, ID> internalJpaIml;

    public CommonRepositoryImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    private SimpleJpaRepository<T, ID> requireInternalJpaIml() {
        if (internalJpaIml == null) {
            internalJpaIml = new SimpleJpaRepository<>(domainClass, entityManager);
        }
        return internalJpaIml;
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
        requireInternalJpaIml().setRepositoryMethodMetadata(crudMethodMetadata);
    }

    @Override
    public List<T> findAll() {
        return requireInternalJpaIml().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return requireInternalJpaIml().findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return requireInternalJpaIml().findAll(pageable);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return requireInternalJpaIml().findAllById(ids);
    }

    @Override
    public long count() {
        return requireInternalJpaIml().count();
    }

    @Override
    public void deleteById(ID id) {
        requireInternalJpaIml().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        requireInternalJpaIml().delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        requireInternalJpaIml().deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        requireInternalJpaIml().deleteAll();
    }

    @Override
    public <S extends T> S save(S entity) {
        return requireInternalJpaIml().save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return requireInternalJpaIml().saveAll(entities);
    }

    @Override
    public Optional<T> findById(ID id) {
        return requireInternalJpaIml().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return requireInternalJpaIml().existsById(id);
    }

    @Override
    public void flush() {
        requireInternalJpaIml().flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return requireInternalJpaIml().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        requireInternalJpaIml().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        requireInternalJpaIml().deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return requireInternalJpaIml().getOne(id);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return requireInternalJpaIml().findOne(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return requireInternalJpaIml().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return requireInternalJpaIml().findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return requireInternalJpaIml().findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return requireInternalJpaIml().count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return requireInternalJpaIml().exists(example);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return requireInternalJpaIml().findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return requireInternalJpaIml().findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return requireInternalJpaIml().findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return requireInternalJpaIml().findAll(spec, sort);
    }

    @Override
    public long count(Specification<T> spec) {
        return requireInternalJpaIml().count(spec);
    }
}
