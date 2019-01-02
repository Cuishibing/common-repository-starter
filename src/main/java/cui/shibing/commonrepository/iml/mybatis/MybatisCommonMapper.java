package cui.shibing.commonrepository.iml.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

public interface MybatisCommonMapper {

    // methods
    String FIND_ALL = MybatisCommonMapper.class.getName() + ".findAll";

    // parameters
    String PARAM_TABLE = "table";
    String PARAM_COLUMNS = "columns";

    @SelectProvider(type = MybatisCommonSqlProvider.class, method = "generateFindAllSql")
    @ResultType(Map.class)
    void findAll(@Param("table") String table, @Param("columns") String[] columns);

}
