package ee.icefire.modelgen.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaPackage implements Serializable {

  public static final JavaPackage DEFAULT_PACKAGE = new JavaPackage("");

  protected String name;
  protected List<JavaClass> javaClasses = new ArrayList<>();

  public JavaPackage(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<JavaClass> getJavaClasses() {
    return Collections.unmodifiableList(javaClasses);
  }

  public void addJavaClass(JavaClass javaClass) {
    javaClass.setPackage(this);
    this.javaClasses.add(javaClass);
  }

}
