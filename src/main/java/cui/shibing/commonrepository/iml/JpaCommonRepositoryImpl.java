package cui.shibing.commonrepository.iml;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cui.shibing.commonrepository.CommonRepository;

public class JpaCommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
	@PersistenceContext
	protected EntityManager entityManager;
	protected Class<T> domainClass;
	private SimpleJpaRepository<T, ID> internalJpaIml;

	public JpaCommonRepositoryImpl(Class<T> domainClass) {
		this.domainClass = domainClass;
		this.internalJpaIml = new SimpleJpaRepository<>(domainClass, entityManager);
	}

	@Override
	public void clear() {
		entityManager.clear();
	}

	@Override
	public List<T> findAll() {
		return this.internalJpaIml.findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return this.internalJpaIml.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return this.internalJpaIml.findAll(pageable);
	}

	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		return this.internalJpaIml.findAllById(ids);
	}

	@Override
	public long count() {
		return this.internalJpaIml.count();
	}

	@Transactional
	@Override
	public void deleteById(ID id) {
		this.internalJpaIml.deleteById(id);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		this.internalJpaIml.delete(entity);
	}

	@Transactional
	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		this.internalJpaIml.deleteAll(entities);
	}

	@Transactional
	@Override
	public void deleteAll() {
		this.internalJpaIml.deleteAll();
	}

	@Transactional
	@Override
	public <S extends T> S save(S entity) {
		return this.internalJpaIml.save(entity);
	}

	@Transactional
	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return this.internalJpaIml.saveAll(entities);
	}

	@Override
	public Optional<T> findById(ID id) {
		return this.internalJpaIml.findById(id);
	}

	@Override
	public boolean existsById(ID id) {
		return this.internalJpaIml.existsById(id);
	}

	@Transactional
	@Override
	public void flush() {
		this.internalJpaIml.flush();
	}

	@Transactional
	@Override
	public <S extends T> S saveAndFlush(S entity) {
		return this.internalJpaIml.saveAndFlush(entity);
	}

	@Transactional
	@Override
	public void deleteInBatch(Iterable<T> entities) {
		this.internalJpaIml.deleteInBatch(entities);
	}

	@Transactional
	@Override
	public void deleteAllInBatch() {
		this.internalJpaIml.deleteAllInBatch();
	}

	@Override
	public T getOne(ID id) {
		return this.internalJpaIml.getOne(id);
	}

	@Override
	public <S extends T> Optional<S> findOne(Example<S> example) {
		return this.internalJpaIml.findOne(example);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return this.internalJpaIml.findAll(example);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return this.internalJpaIml.findAll(example, sort);
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return this.internalJpaIml.findAll(example, pageable);
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		return this.internalJpaIml.count(example);
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		return this.internalJpaIml.exists(example);
	}

	@Override
	public Optional<T> findOne(Specification<T> spec) {
		return this.internalJpaIml.findOne(spec);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return this.internalJpaIml.findAll(spec);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return this.internalJpaIml.findAll(spec, pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return this.internalJpaIml.findAll(spec, sort);
	}

	@Override
	public long count(Specification<T> spec) {
		return this.internalJpaIml.count(spec);
	}
}
