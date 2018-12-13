package ee.icefire.modelgen.db.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseModel {

  protected List<Schema> schemas = new ArrayList<>();

  public List<Schema> getSchemas() {
    return schemas;
  }

  public Schema findSchema(String schemaName) {
    for (Schema schema : getSchemas()) {
      if (schema.getName().equals(schemaName)) {
        return schema;
      }
    }
    return null;
  }

}
