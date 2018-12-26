package cui.shibing;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的事务管理者,集成JpaTransactionManager和DatasourceTransactionManager
 * 分别用于JPA和Mybatis的事务管理
 * 
 * 当JPA和Mybatis同时存在时Spring默认使用的事务管理是JpaTransactionManager,对Mybatis不起作用
 */
@Configuration
@AutoConfigureBefore(HibernateJpaAutoConfiguration.class)
public class ComposedTransactionManagerAutoConfiguration {
	
	
	
}
