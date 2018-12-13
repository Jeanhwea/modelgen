package ee.icefire.modelgen.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Annotation implements Packagable {

  protected String name;
  protected String packageName;

  public Annotation(String name) {
    this.name = name;
  }

  public Annotation(String name, String packageName) {
    this.name = name;
    this.packageName = packageName;
  }

  public String getName() {
    return name;
  }

  @Override
  public String getPackageName() {
    return packageName;
  }

  public static class Attribute {
    String name;
    String value;

    public Attribute(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public String getValue() {
      return value;
    }
  }

  protected List<Attribute> attributes = new ArrayList<>();

  public void addAttribute(Attribute attribute) {
    this.attributes.add(attribute);
  }

  public List<Attribute> getAttributes() {
    return Collections.unmodifiableList(attributes);
  }

  public boolean hasAttributes() {
    return attributes.size() > 0;
  }

  public Annotation attr(String name, String value) {
    attributes.add(new Attribute(name, value));
    return this;
  }

  public Annotation attrIf(String name, String value, boolean condition) {
    return condition ? attr(name, value) : this;
  }

  public Annotation attrIf(String name, String value) {
    return value != null ? attr(name, value) : this;
  }

  @Override
  public String toString() {
    return "@" + name;
  }

  public static Annotation annotation(String name) {
    return new Annotation(name);
  }
}
