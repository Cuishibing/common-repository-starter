package cui.shibing.transaction;

import javax.activation.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class ComposedTransaction implements PlatformTransactionManager, BeanFactoryAware {

	@Autowired
	private JpaTransactionManager jpaTransactionManager;

	private DataSourceTransactionManager dataSourceTransactionManager;

	private DataSource dataSource;

	public ComposedTransaction(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}

	@Override
	public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

}
