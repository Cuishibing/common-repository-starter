package cui.shibing.commonrepository.iml;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cui.shibing.commonrepository.CommonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class JpaCommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
	@PersistenceContext
	protected EntityManager entityManager;
	protected Class<T> domainClass;
	private SimpleJpaRepository<T, ID> internalJpaIml;

	public JpaCommonRepositoryImpl(Class<T> domainClass) {
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

	@Transactional
	@Override
	public void deleteById(ID id) {
		requireInternalJpaIml().deleteById(id);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		requireInternalJpaIml().delete(entity);
	}

	@Transactional
	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		requireInternalJpaIml().deleteAll(entities);
	}

	@Transactional
	@Override
	public void deleteAll() {
		requireInternalJpaIml().deleteAll();
	}

	@Transactional
	@Override
	public <S extends T> S save(S entity) {
		return requireInternalJpaIml().save(entity);
	}

	@Transactional
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

	@Transactional
	@Override
	public void flush() {
		requireInternalJpaIml().flush();
	}

	@Transactional
	@Override
	public <S extends T> S saveAndFlush(S entity) {
		return requireInternalJpaIml().saveAndFlush(entity);
	}

	@Transactional
	@Override
	public void deleteInBatch(Iterable<T> entities) {
		requireInternalJpaIml().deleteInBatch(entities);
	}

	@Transactional
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