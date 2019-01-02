package cui.shibing.commonrepository.iml.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommonListResultHandler<T> implements ResultHandler<T> {

    private List<T> result = new ArrayList<>();

    private Class<T> domainClass;

    private Configuration configuration;

    public CommonListResultHandler(Configuration configuration, Class<T> domainClass) {
        super();
        this.domainClass = domainClass;
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleResult(ResultContext<? extends T> resultContext) {
        Object resultObject = resultContext.getResultObject();

        Map<String, Object> resultMap = (Map<String, Object>) resultObject;
        try {
            T oneRow = domainClass.newInstance();
            MetaObject metaObject = configuration.newMetaObject(oneRow);
            resultMap.forEach((key, value) -> {
                String findProperty = metaObject.findProperty(key, configuration.isMapUnderscoreToCamelCase());
                if (findProperty != null && metaObject.hasSetter(findProperty)) {
                    Class<?> type = metaObject.getSetterType(findProperty);
                    if (value != null) {
                        Object translatedValue = null;
                        if (type.equals(value.getClass())) {// 类型匹配的情况
                            translatedValue = value;
                        } else {// 类型不匹配，尝试转换
                            if (type.equals(String.class)) {// 字符串
                                translatedValue = value.toString();
                            } else if (type.equals(Integer.class)) {
                                try {
                                    translatedValue = Integer.parseInt(value.toString());
                                } catch (NumberFormatException e) {
                                    // ignore
                                }
                            } else if (type.equals(Double.class)) {
                                try {
                                    translatedValue = Double.parseDouble(value.toString());
                                } catch (NumberFormatException e) {
                                    // ignore
                                }
                            } else if (type.equals(Float.class)) {
                                try {
                                    translatedValue = Float.parseFloat(value.toString());
                                } catch (NumberFormatException e) {
                                    // ignore
                                }
                            } else if (type.equals(Date.class)) {
                                Class<?> valueClass = value.getClass();
                                if (valueClass.equals(Timestamp.class)) {
                                    Timestamp t = (Timestamp) value;
                                    translatedValue = new Date(t.getTime());
                                } else if (valueClass.equals(java.sql.Date.class)) {
                                    java.sql.Date t = (java.sql.Date) value;
                                    translatedValue = new Date(t.getTime());
                                }
                            }

                        }
                        metaObject.setValue(findProperty, translatedValue);
                    }
                }
            });

            result.add(oneRow);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public List<T> getResult() {
        return result;
    }

}
