package cui.shibing.commonrepository.iml.mybatis;

import org.apache.ibatis.jdbc.SQL;

public class MybatisCommonSqlProvider {
	public String generateFindAllSql(String table,String[] columns) {
		return new SQL()
				.SELECT(columns)
				.FROM(table).toString();
	}
}
