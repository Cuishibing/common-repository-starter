package cui.shibing.commonrepository.iml.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

public interface MybatisCommonRepository<T> {
	
	String findAll = MybatisCommonRepository.class.getName()+".findAll";
	
	@SelectProvider(type = MybatisCommonSqlProvider.class,method="generateFindAllSql")
	@ResultType(Map.class)
	void findAll(String table,String[] columns);
	
}
