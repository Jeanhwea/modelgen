package ee.icefire.modelgen.db.model;

public class ForeignKey {

  Column column;
  Column referenceColumn;

  public Column getColumn() {
    return column;
  }

  public void setColumn(Column column) {
    this.column = column;
  }

  public Column getReferenceColumn() {
    return referenceColumn;
  }

  public void setReferenceColumn(Column referenceColumn) {
    this.referenceColumn = referenceColumn;
  }
}
