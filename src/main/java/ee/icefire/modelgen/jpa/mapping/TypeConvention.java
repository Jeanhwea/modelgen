package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.db.model.Column;

public interface TypeConvention {

  public String typeForColumn(Column column);

}
