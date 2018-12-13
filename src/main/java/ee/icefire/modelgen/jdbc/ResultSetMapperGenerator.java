package ee.icefire.modelgen.jdbc;


import ee.icefire.modelgen.java.ClassUtils;
import ee.icefire.modelgen.jpa.model.BasicProperty;
import ee.icefire.modelgen.jpa.model.Entity;
import ee.icefire.modelgen.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ResultSetMapperGenerator {

  public static final Map<String, String> RESULTSET_GETTERS = new HashMap<>();

  public static void map(String type, String getter) {
    RESULTSET_GETTERS.put(type, getter);

  }

  static {
    map("byte", "Byte");
    map("Byte", "Byte");
    map("short", "Short");
    map("Short", "Short");
    map("int", "Int");
    map("Int", "Int");
    map("long", "Long");
    map("Long", "Long");
    map("float", "Float");
    map("Float", "Float");
    map("double", "Double");
    map("Double", "Double");
    map("String", "String");
    map("boolean", "Boolean");
    map("Boolean", "Boolean");
    map("BigDecimal", "BigDecimal");
    map("Date", "Date");
  }

  public String generateCode(Entity entity, String entityVar, String rsVar) {
    StringBuilder sb = new StringBuilder();

    sb.append(String.format("%s %s = new %s();\n", entity.getClassName(), entityVar, entity.getClassName()));

    for (BasicProperty property : entity.getBasicProperties()) {
      generatePropertyMapping(sb, property, entityVar, rsVar);
    }

    sb.append(format("return %s;", entityVar));
    return sb.toString();
  }

  public void generatePropertyMapping(StringBuilder sb, BasicProperty property, String entityVar, String rsVar) {
    if (!property.isWritable()) return;
    String getter = RESULTSET_GETTERS.get(property.getType());
    if (getter == null) getter = "Object";
    sb.append(format("%s.%s(%s.get%s(%s));\n", entityVar, property.getSetter().getName(), rsVar, getter, StringUtils.quot(property.getMappedColumn().getName())));

    if (property.isNullable() && !ClassUtils.isBasicType(property.getType())) {
      sb.append(format("if (%s.wasNull()) %s.%s(null);\n", rsVar, entityVar, property.getSetter().getName()));
    }
  }

}
