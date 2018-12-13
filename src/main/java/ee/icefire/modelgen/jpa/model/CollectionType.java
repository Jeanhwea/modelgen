package ee.icefire.modelgen.jpa.model;

public enum CollectionType {

  LIST("List"),
  MAP("Map"),
  SET("Set");

  private String className;

  private CollectionType(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }
}
