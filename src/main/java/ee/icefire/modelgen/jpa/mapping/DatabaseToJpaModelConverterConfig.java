package ee.icefire.modelgen.jpa.mapping;

import java.util.Map;

public class DatabaseToJpaModelConverterConfig {

  protected String basePackage;

  protected boolean convertSchemaToPackage;

  protected NamingConvention namingConvention;
  protected TypeConvention typeConvention;
  protected JpaModelCustomizer jpaModelCustomizer;

  protected boolean relationMappingEnabled;
  protected String entitiesBaseClass;
  protected String entitiesBaseClassImport;
  protected boolean entitiesImplementSerializable;
  protected boolean entitiesIncludeSchema;
  protected boolean entitiesEqualById;
  protected boolean useFieldLevelAnnotations;

  protected String sequencePrefix = "";
  protected String sequenceSuffix = "";

  protected Map<String, String> entityNameOverrides;
  protected Map<String, String> sequenceNameOverrides;

  protected Integer defaultAllocationSize;
  protected boolean placeAnnotationsOnGetters;
  protected boolean placeCommentsOnGetters;
  protected boolean addGeneratedAnnotation = true;

  protected boolean generateToStringMethod;

  public String getBasePackage() {
    return basePackage;
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  public NamingConvention getNamingConvention() {
    return namingConvention;
  }

  public void setNamingConvention(NamingConvention namingConvention) {
    this.namingConvention = namingConvention;
  }

  public TypeConvention getTypeConvention() {
    return typeConvention;
  }

  public void setTypeConvention(TypeConvention typeConvention) {
    this.typeConvention = typeConvention;
  }

  public JpaModelCustomizer getJpaModelCustomizer() {
    return jpaModelCustomizer;
  }

  public void setJpaModelCustomizer(JpaModelCustomizer jpaModelCustomizer) {
    this.jpaModelCustomizer = jpaModelCustomizer;
  }

  public boolean isConvertSchemaToPackage() {
    return convertSchemaToPackage;
  }

  public void setConvertSchemaToPackage(boolean convertSchemaToPackage) {
    this.convertSchemaToPackage = convertSchemaToPackage;
  }

  public boolean isRelationMappingEnabled() {
    return relationMappingEnabled;
  }

  public void setRelationMappingEnabled(boolean relationMappingEnabled) {
    this.relationMappingEnabled = relationMappingEnabled;
  }

  public String getEntitiesBaseClass() {
    return entitiesBaseClass;
  }

  public void setEntitiesBaseClass(String entitiesBaseClass) {
    this.entitiesBaseClass = entitiesBaseClass;
  }

  public String getEntitiesBaseClassImport() {
    return entitiesBaseClassImport;
  }

  public void setEntitiesBaseClassImport(String entitiesBaseClassImport) {
    this.entitiesBaseClassImport = entitiesBaseClassImport;
  }

  public boolean isEntitiesImplementSerializable() {
    return entitiesImplementSerializable;
  }

  public void setEntitiesImplementSerializable(boolean entitiesImplementSerializable) {
    this.entitiesImplementSerializable = entitiesImplementSerializable;
  }

  public boolean isEntitiesIncludeSchema() {
    return entitiesIncludeSchema;
  }

  public void setEntitiesIncludeSchema(boolean entitiesIncludeSchema) {
    this.entitiesIncludeSchema = entitiesIncludeSchema;
  }

  public boolean isEntitiesEqualById() {
    return entitiesEqualById;
  }

  public void setEntitiesEqualById(boolean entitiesEqualById) {
    this.entitiesEqualById = entitiesEqualById;
  }

  public boolean isUseFieldLevelAnnotations() {
    return useFieldLevelAnnotations;
  }

  public void setUseFieldLevelAnnotations(boolean useFieldLevelAnnotations) {
    this.useFieldLevelAnnotations = useFieldLevelAnnotations;
  }

  public Map<String, String> getEntityNameOverrides() {
    return entityNameOverrides;
  }

  public void setEntityNameOverrides(Map<String, String> entityNameOverrides) {
    this.entityNameOverrides = entityNameOverrides;
  }

  public Map<String, String> getSequenceNameOverrides() {
    return sequenceNameOverrides;
  }

  public void setSequenceNameOverrides(Map<String, String> sequenceNameOverrides) {
    this.sequenceNameOverrides = sequenceNameOverrides;
  }

  public String getSequencePrefix() {
    return sequencePrefix;
  }

  public void setSequencePrefix(String sequencePrefix) {
    this.sequencePrefix = sequencePrefix;
  }

  public String getSequenceSuffix() {
    return sequenceSuffix;
  }

  public void setSequenceSuffix(String sequenceSuffix) {
    this.sequenceSuffix = sequenceSuffix;
  }

  public Integer getDefaultAllocationSize() {
    return defaultAllocationSize;
  }

  public void setDefaultAllocationSize(Integer defaultAllocationSize) {
    this.defaultAllocationSize = defaultAllocationSize;
  }

  public boolean isPlaceAnnotationsOnGetters() {
    return placeAnnotationsOnGetters;
  }

  public void setPlaceAnnotationsOnGetters(boolean placeAnnotationsOnGetters) {
    this.placeAnnotationsOnGetters = placeAnnotationsOnGetters;
  }

  public boolean isPlaceCommentsOnGetters() {
    return placeCommentsOnGetters;
  }

  public void setPlaceCommentsOnGetters(boolean placeCommentsOnGetters) {
    this.placeCommentsOnGetters = placeCommentsOnGetters;
  }

  public boolean isAddGeneratedAnnotation() {
    return addGeneratedAnnotation;
  }

  public void setAddGeneratedAnnotation(boolean addGeneratedAnnotation) {
    this.addGeneratedAnnotation = addGeneratedAnnotation;
  }

  public boolean isGenerateToStringMethod() {
    return generateToStringMethod;
  }

  public void setGenerateToStringMethod(boolean generateToStringMethod) {
    this.generateToStringMethod = generateToStringMethod;
  }

}
