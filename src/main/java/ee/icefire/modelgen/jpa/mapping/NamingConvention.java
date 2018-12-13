package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.db.model.Table;
import ee.icefire.modelgen.jpa.model.Entity;
import ee.icefire.modelgen.jpa.model.AssociationType;

public interface NamingConvention {

  public String entityName(Table table);

  public String propertyName(Column column);

  public String associationName(Entity relatedEntity, AssociationType associationType);

}
