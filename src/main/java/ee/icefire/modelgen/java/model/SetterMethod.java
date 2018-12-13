package ee.icefire.modelgen.java.model;

import ee.icefire.modelgen.jpa.model.AccessModifier;
import ee.icefire.modelgen.utils.StringUtils;

import java.util.Collections;
import java.util.List;

public class SetterMethod extends Method {

  protected Field field;

  public SetterMethod(Field field) {
    super();
    this.access = AccessModifier.PUBLIC;
    this.field = field;
  }

  @Override
  public String getName() {
    return deriveSetterName(field.getName());
  }

  @Override
  public String getReturnType() {
    return null;
  }

  protected String generateBody() {
    return "this." + field.getName() + " = " + field.getName() + ";";
  }

  @Override
  public String getBody() {
    return body != null ? super.getBody() : generateBody();
  }

  @Override
  public List<Argument> getArguments() {
    return Collections.singletonList(new Argument(field.getType(), field.getName()));
  }

  public static String deriveSetterName(String name) {
    return "set" + StringUtils.capitalize(name);
  }

}
