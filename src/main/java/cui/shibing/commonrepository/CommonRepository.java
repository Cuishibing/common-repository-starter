package cui.shibing.commonrepository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

/**
 * 导出通用Repository接口
 * */
@NoRepositoryBean
public interface CommonRepository<T,ID> extends JpaRepositoryImplementation<T,ID> {
    void clear();
}
