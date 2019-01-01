package cui.shibing.commonrepository.iml.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.ReflectionUtils;

import cui.shibing.commonrepository.CommonRepository;

public class MybatisCommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {

	private Class<T> domainClass;

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	private Configuration configuration;
	
	private boolean isMapUnderscoreToCamelCase = false;
	
	public MybatisCommonRepositoryImpl(Class<T> domainClass) {
		this.domainClass = domainClass;
	}
	
	
	@PostConstruct
	public void postInit() {
		configuration = sessionFactory.getConfiguration();
		if(!configuration.hasMapper(MybatisCommonRepository.class)) {
			configuration.addMapper(MybatisCommonRepository.class);	
		}
		
		isMapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();

	}
	
	@Override
	public List<T> findAll() {
	
		Entity entityAnnEntity = domainClass.getAnnotation(Entity.class);
		String tableName = entityAnnEntity.name();
		List<T> result = new ArrayList<>();
		try(SqlSession session = sessionFactory.openSession()){
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("table", tableName);
			parameterMap.put("columns", EntityInfoHolder.getEntityInfo(domainClass, isMapUnderscoreToCamelCase).getAllColumnNames());
			
			session.select(MybatisCommonRepository.findAll, parameterMap,new ResultHandler() {

				@Override
				public void handleResult(ResultContext resultContext) {
					Object resultObject = resultContext.getResultObject();
					
					Map<String, Object> resultMap = (Map<String, Object>) resultObject;
					try {
						T oneRow = domainClass.newInstance();
						MetaObject metaObject = configuration.newMetaObject(oneRow);
						resultMap.forEach((key,value)->{
							String findProperty = metaObject.findProperty(key, isMapUnderscoreToCamelCase);
							if(findProperty != null && metaObject.hasSetter(findProperty)) {
								Class<?> type = metaObject.getSetterType(findProperty);
								if(value != null && type.equals(value.getClass())) {
									metaObject.setValue(findProperty, value);
								}
							}
						});
						
						result.add(oneRow);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				
				
			});
			return result;
		}
	}

	@Override
	public List<T> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public T getOne(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(ID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends T> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<T> findOne(Specification<T> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<T> spec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
