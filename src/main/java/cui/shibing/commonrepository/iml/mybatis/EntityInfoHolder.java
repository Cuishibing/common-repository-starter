package cui.shibing.commonrepository.iml.mybatis;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.*;

public class EntityInfoHolder {
    private static final Map<Class<?>, EntityInfo> ENTITY_INFO_CACHE = new HashMap<>();

    public static EntityInfo getEntityInfo(Class<?> entityClass, boolean isDealUnderscoreAndCamelCase) {
        return ENTITY_INFO_CACHE.computeIfAbsent(entityClass, (key) -> {
            EntityInfo entityInfo = new EntityInfo();

            for (Field f : getPojoAllDeclaredFields(entityClass)) {
                if (f.getAnnotation(Column.class) != null || f.getAnnotation(Id.class) != null) {
                    boolean isId = f.getAnnotation(Id.class) != null;
                    entityInfo.addColumn(f, isId, isDealUnderscoreAndCamelCase);
                }
            }
            return entityInfo;
        });
    }

    public static class EntityInfo {
        private Map<String, Field> columnNameFieldMap = new HashMap<>();
        private Set<Field> columns = new HashSet<>();
        private String idColumnName;

        public void addColumn(Field f, boolean isId, boolean isDealUnderscoreAndCamelCase) {
            if (!columns.contains(f)) {
                columns.add(f);
                Column column = f.getAnnotation(Column.class);
                String columnName = null;
                if (column != null)
                    columnName = column.name();
                if (StringUtils.isEmpty(columnName)) {
                    columnName = f.getName();
                    if (isDealUnderscoreAndCamelCase) {
                        columnName = camelCaseToUnderscore(columnName);
                    }
                }

                columnNameFieldMap.put(columnName, f);
                if (isId) {
                    idColumnName = columnName;
                }
            }
        }

        public String getIdColumnName() {
            return idColumnName;
        }

        public String[] getAllColumnNames() {
            Set<String> columnNames = columnNameFieldMap.keySet();
            String[] columnNameArray = new String[columnNames.size()];
            return columnNames.toArray(columnNameArray);
        }
    }

    /**
     * 驼峰转下划线
     */
    public static String camelCaseToUnderscore(String src) {
        if (src == null) {
            return null;
        }
        if (src.equals("")) {
            return "";
        }
        char[] charArray = src.toCharArray();
        char preChar = charArray[0];
        StringBuilder sb = new StringBuilder();
        sb.append(preChar);
        for (int i = 1; i < charArray.length; i++) {
            char currentChar = charArray[i];
            if (Character.isLowerCase(preChar) && Character.isUpperCase(currentChar)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(currentChar));
        }
        return sb.toString();
    }

    public static List<Field> getPojoAllDeclaredFields(Class<?> clazz) {
        List<Field> result = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> superclass = clazz.getSuperclass();
        if (!superclass.equals(Object.class)) {
            result.addAll(getPojoAllDeclaredFields(superclass));
        }
        return result;
    }
}
