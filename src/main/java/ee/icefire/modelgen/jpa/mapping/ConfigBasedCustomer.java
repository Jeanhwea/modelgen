package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.jpa.model.JpaModel;

public class ConfigBasedCustomer implements JpaModelCustomizer {

  protected DatabaseToJpaModelConverterConfig config;

  public ConfigBasedCustomer(DatabaseToJpaModelConverterConfig config) {
    this.config = config;
  }

  @Override
  public void customize(JpaModel jpaModel) {
    jpaModel.getEntities().forEach(entity -> {
        if (config.getEntitiesBaseClassImport() != null) {
          entity.getImports().add(config.getEntitiesBaseClassImport());
        }
      });
  }
}
