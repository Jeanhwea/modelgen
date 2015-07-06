package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.db.model.Column;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DefaultTypeConvention implements TypeConvention {

    public final static Map<Integer, String> DEFAULT_TYPE_MAPPINGS = new HashMap<Integer, String>() {{
        put(Types.INTEGER, "java.lang.Integer");
        put(Types.VARCHAR, "java.lang.String");
        put(Types.BIGINT, "java.lang.Long");
        put(Types.DECIMAL, "java.math.BigDecimal");
        put(Types.DATE, "java.util.Date");
        put(Types.TIMESTAMP, "java.util.Date");
        put(Types.CLOB, "byte[]");
        put(Types.TIME, "java.sql.Time");
        put(Types.BOOLEAN, "Boolean");
    }};

    @Override
    public String typeForColumn(Column column) {
        if (!DEFAULT_TYPE_MAPPINGS.containsKey(column.getType())) {
            throw new RuntimeException("Could not map type " + column.getTypeName() + " for column " + column);
        }
        return DEFAULT_TYPE_MAPPINGS.get(column.getType());
    }

}
