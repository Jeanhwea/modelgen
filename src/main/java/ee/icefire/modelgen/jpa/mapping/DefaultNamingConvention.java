package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.db.model.Table;
import ee.icefire.modelgen.jpa.JpaEntityGeneratorConfig;
import ee.icefire.modelgen.jpa.model.Entity;
import ee.icefire.modelgen.utils.StringUtils;
import ee.icefire.modelgen.jpa.model.AssociationType;

import static ee.icefire.modelgen.jpa.model.AssociationType.isToMany;

/**
 * Camel-case name customizer
 */
public class DefaultNamingConvention implements NamingConvention {

  protected JpaEntityGeneratorConfig config;

  public DefaultNamingConvention(JpaEntityGeneratorConfig config) {
    this.config = config;
  }

  @Override
  public String entityName(Table table) {
    String className = null;
    if (config.getEntityNameOverrides() != null) {
      className = config.getEntityNameOverrides().get(table.getName());
    }
    if (className != null) return className;
    return StringUtils.toCamelCase(table.getName());
  }

  @Override
  public String propertyName(Column column) {
    return StringUtils.decapitalize(StringUtils.toCamelCase(column.getName()));
  }

  @Override
  public String associationName(Entity relatedEntity, AssociationType associationType) {
    return StringUtils.decapitalize(relatedEntity.getClassName()) + (isToMany(associationType) ? "s" : "");
  }
}
