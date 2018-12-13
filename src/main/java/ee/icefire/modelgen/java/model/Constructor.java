package ee.icefire.modelgen.java.model;

import java.util.List;

import static java.util.Collections.emptyList;

public class Constructor extends Method {

  public static final String EMPTY_BODY = "// default constructor";

  protected JavaClass javaClass;
  protected boolean defaultConstructor = true;

  public Constructor(JavaClass javaClass) {
    this.javaClass = javaClass;
  }

  @Override
  public String getName() {
    return javaClass.getClassName();
  }

  @Override
  public List<Argument> getArguments() {
    return emptyList();
  }

  public boolean isDefaultConstructor() {
    return defaultConstructor;
  }

  @Override
  public String getBody() {
    return EMPTY_BODY;
  }
}
