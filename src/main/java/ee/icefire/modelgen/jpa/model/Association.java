package ee.icefire.modelgen.jpa.model;

public class Association {

  protected AssociationProperty source;
  protected AssociationProperty target;

  public Association(AssociationProperty source, AssociationProperty target) {
    this.source = source;
    this.target = target;
  }

  public AssociationProperty getSource() {
    return source;
  }

  public AssociationProperty getTarget() {
    return target;
  }

  public Association cascade(CascadeType sourceCascade, CascadeType targetCascade) {
    source.setCascade(sourceCascade);
    target.setCascade(targetCascade);
    return this;
  }

  public Association name(String sourceName) {
    if (sourceName != null) {
      getSource().setName(sourceName);
    }
    return this;
  }

  public Association names(String sourceName, String targetName) {
    name(sourceName);
    if (targetName != null) {
      getTarget().setName(targetName);
    }
    return this;
  }

  public Association orderBy(String orderBy) {
    getSource().orderBy(orderBy);
    return this;
  }

  public Association batchSize(int batchSize) {
    getSource().setBatchSize(batchSize);
    return this;
  }


}
