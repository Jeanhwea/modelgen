package ee.icefire.modelgen.db.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

  protected Schema schema;

  protected String name;
  protected String remarks;
  protected List<Column> columns = new ArrayList<>();
  protected List<ForeignKey> foreignKeys = new ArrayList<>();
  protected boolean view;

  public Table(Schema schema) {
    this.schema = schema;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Schema getSchema() {
    return schema;
  }

  public List<Column> getColumns() {
    return columns;
  }

  public List<ForeignKey> getForeignKeys() {
    return foreignKeys;
  }

  public boolean isView() {
    return view;
  }

  public void setView(boolean view) {
    this.view = view;
  }

  public Column findColumn(String columnName) {
    for (Column column : getColumns()) {
      if (column.getName().equals(columnName)) {
        return column;
      }
    }
    return null;
  }

  public String getQualifiedName() {
    if (getSchema() != null) {
      return getSchema().getName() + "." + getName();
    } else {
      return getName();
    }
  }

  @Override
  public String toString() {
    return getQualifiedName();
  }
}
