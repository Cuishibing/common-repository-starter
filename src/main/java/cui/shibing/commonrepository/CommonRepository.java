package cui.shibing.commonrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 导出通用Repository接口
 * */
@NoRepositoryBean
public interface CommonRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>{
	
	/**
	 * 如果使用的是JpaCommonRepositoryImpl，则相当于entityManager.clear();
	 * */
    void clear();
}
