package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.java.model.Annotation;
import ee.icefire.modelgen.java.model.Property;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static ee.icefire.modelgen.java.model.Annotation.annotation;
import static ee.icefire.modelgen.utils.StringUtils.quot;

public class AssociationProperty extends Property {

  protected AssociationType associationType;
  protected Entity relatedEntity;

  protected String orderBy;
  protected List<JoinColumn> joinColumns = new ArrayList<>();
  protected JoinTable joinTable;

  protected boolean includeInverse = true;
  protected FetchType fetchType;
  protected FetchMode fetchMode;
  protected CascadeType cascade;
  protected CollectionType collectionType = CollectionType.LIST;
  protected boolean orphanRemoval = true;

  protected AssociationProperty mappedBy;
  private int batchSize;

  public AssociationProperty(Entity entity, AssociationType associationType, Entity relatedEntity, String name) {
    super(entity, relatedEntity.getClassName(), name);

    if (associationType == null) {
      throw new IllegalArgumentException("Null association type specified");
    }
    this.associationType = associationType;
    this.relatedEntity = relatedEntity;
  }

  public AssociationType getAssociationType() {
    return associationType;
  }

  public void setAssociationType(AssociationType associationType) {
    this.associationType = associationType;
  }

  public Entity getRelatedEntity() {
    return relatedEntity;
  }

  public void setRelatedEntity(Entity relatedEntity) {
    this.relatedEntity = relatedEntity;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public AssociationProperty orderBy(String orderBy) {
    this.setOrderBy(orderBy);
    return this;
  }

  public List<JoinColumn> getJoinColumns() {
    return joinColumns;
  }

  public AssociationProperty joinColumn(Column foreignKeycolumn, Column referencedColumn) {
    joinColumns.add(new JoinColumn(this, foreignKeycolumn, referencedColumn));
    return this;
  }

  public JoinTable getJoinTable() {
    return joinTable;
  }

  public void setJoinTable(JoinTable joinTable) {
    this.joinTable = joinTable;
  }

  public AssociationProperty getMappedBy() {
    return mappedBy;
  }

  public void setMappedBy(AssociationProperty mappedBy) {
    this.mappedBy = mappedBy;
  }

  public AssociationProperty mappedBy(AssociationProperty property) {
    setMappedBy(property);
    return this;
  }

  public boolean isIncludeInverse() {
    return includeInverse;
  }

  public void setIncludeInverse(boolean includeInverse) {
    this.includeInverse = includeInverse;
  }

  public FetchType getFetchType() {
    return fetchType;
  }

  public void setFetchType(FetchType fetchType) {
    this.fetchType = fetchType;
  }

  public AssociationProperty fetchType(FetchType fetchType) {
    setFetchType(fetchType);
    return this;
  }

  public FetchMode getFetchMode() {
    return fetchMode;
  }

  public void setFetchMode(FetchMode fetchMode) {
    this.fetchMode = fetchMode;
  }

  public CascadeType getCascade() {
    return cascade;
  }

  public void setCascade(CascadeType cascade) {
    this.cascade = cascade;
  }

  public CollectionType getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(CollectionType collectionType) {
    this.collectionType = collectionType;
  }

  public boolean isOrphanRemoval() {
    return orphanRemoval;
  }

  public void setOrphanRemoval(boolean orphanRemoval) {
    this.orphanRemoval = orphanRemoval;
  }

  public boolean isOneToOne() {
    return this.getAssociationType().equals(AssociationType.ONE_TO_ONE);
  }

  public boolean isInverseOneToOne() {
    return isOneToOne() && getMappedBy() != null;
  }

  public boolean isOneToMany() {
    return this.getAssociationType().equals(AssociationType.ONE_TO_MANY);
  }

  public boolean isManyToOne() {
    return this.getAssociationType().equals(AssociationType.MANY_TO_ONE);
  }

  public boolean isManyToMany() {
    return this.getAssociationType().equals(AssociationType.MANY_TO_MANY);
  }

  public boolean isOneTo() {
    return isOneToMany() || isOneToOne();
  }

  public boolean isToMany() {
    return isManyToMany() || isOneToMany();
  }

  @Override
  public String getType() {
    if (isToMany()) {
      return getCollectionType().getClassName() + "<" + getRelatedEntity().getClassName() + ">";
    } else {
      return getRelatedEntity().getClassName();
    }
  }

  @Override
  public String getDefaultValue() {
    if (isOneToMany() && getCollectionType() == CollectionType.LIST && super.getDefaultValue() == null) {
      return "new ArrayList<" + getRelatedEntity().getClassName() + ">()";
    } else {
      return super.getDefaultValue();
    }
  }

  public void setBatchSize(int batchSize) {
    this.batchSize = batchSize;
  }

  public int getBatchSize() {
    return batchSize;
  }

  @Override
  protected List<Annotation> generateAnnotations() {
    List<Annotation> annotations = new ArrayList<>(super.generateAnnotations());
    if (isOneToMany() || isInverseOneToOne()) {
      annotations.add(annotation(getAssociationType().getName())
                      .attr("mappedBy", quot(getMappedBy().getName()))
                      .attrIf("cascade", getCascade() != null ? getCascade().toJavaTypeString() : null)
                      );

      if (getBatchSize() > 0) {
        annotations.add(annotation("org.hibernate.annotations.BatchSize").attr("size", Integer.toString(getBatchSize())));
      }
    } else {
      annotations.add(annotation(getAssociationType().getName())
                      .attrIf("cascade", getCascade() != null ? getCascade().toJavaTypeString() : null)
                      .attrIf("fetch", getFetchType() != null ? getFetchType().toJavaTypeString() : null)
                      );
    }
    if (getJoinColumns().size() == 1) {
      JoinColumn joinColumn = getJoinColumns().get(0);
      if (joinColumn.getForeignKeyColumn() == null) {
        throw new IllegalStateException(format("No foreign key column in join column for field %s", joinColumn.getField().getQualifiedName()));
      }

      annotations.add(annotation("JoinColumn")
                      .attr("name", quot(joinColumn.getForeignKeyColumn().getName()))
                      .attrIf("referencedColumnName", joinColumn.getReferencedColumn() != null ? quot(joinColumn.getReferencedColumn().getName()) : null)
                      );

    }
    if (getOrderBy() != null) {
      annotations.add(annotation(format("OrderBy(\"%s\")", getOrderBy())));
    }
    return annotations;
  }
}
