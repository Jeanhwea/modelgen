package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.jpa.JpaEntityGeneratorConfig;
import ee.icefire.modelgen.jpa.model.Association;
import ee.icefire.modelgen.jpa.model.AssociationProperty;
import ee.icefire.modelgen.jpa.model.JpaModel;
import ee.icefire.modelgen.jpa.AssociationHelper;

public abstract class JpaAssociationCustomizer implements JpaModelCustomizer {

  AssociationHelper associations;
  JpaEntityGeneratorConfig options;

  public JpaAssociationCustomizer(JpaEntityGeneratorConfig options) {
    this.options = options;
  }

  @Override
  public void customize(JpaModel jpaModel) {
    associations = new AssociationHelper(jpaModel, options);
    customizeAssociations(jpaModel);
  }

  public abstract void customizeAssociations(JpaModel jpaModel);

  public AssociationProperty addOneToMany(String from, String to, AssociationProperty mappedBy) {
    return associations.addOneToMany(from, to, mappedBy);
  }

  public AssociationProperty addOneToOneInverse(String source, String target, AssociationProperty mappedBy) {
    return associations.addOneToOneInverse(source, target, mappedBy);
  }

  public Association addOneToOneBoth(String source, String target, String joinColumnName) {
    return associations.addOneToOneBoth(source, target, joinColumnName);
  }

  public Association addOneToManyBoth(String source, String target, String joinColumnName) {
    return associations.addOneToManyBoth(source, target, joinColumnName);
  }

  public Association addOneToManyBoth(String from, String to, String joinColumnName, String referencedColumnName) {
    return associations.addOneToManyBoth(from, to, joinColumnName, referencedColumnName);
  }

  public AssociationProperty addOneToOne(String source, String target, String joinColumnName, String referencedColumnName) {
    return associations.addOneToOne(source, target, joinColumnName, referencedColumnName);
  }

  public AssociationProperty addManyToOne(String source, String target, String joinColumnName) {
    return associations.addManyToOne(source, target, joinColumnName);
  }

  public AssociationProperty addManyToOne(String from, String to, String joinColumnName, String referencedColumnName) {
    return associations.addManyToOne(from, to, joinColumnName, referencedColumnName);
  }

  public AssociationProperty addOneToOne(String from, String to, String joinColumnName) {
    return associations.addOneToOne(from, to, joinColumnName);
  }
}
