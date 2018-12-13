package ee.icefire.modelgen.jdbc;

import ee.icefire.modelgen.java.code.JavaCodeGeneratorConfig;

public class MapperGeneratorConfig {

  protected JavaCodeGeneratorConfig javaCodeGeneratorConfig = new JavaCodeGeneratorConfig();

  protected String baseClass;
  protected String classNameSuffix = "Mapper";

  public JavaCodeGeneratorConfig getJavaCodeGeneratorConfig() {
    return javaCodeGeneratorConfig;
  }

  public void setJavaCodeGeneratorConfig(JavaCodeGeneratorConfig javaCodeGeneratorConfig) {
    this.javaCodeGeneratorConfig = javaCodeGeneratorConfig;
  }

  public String getBaseClass() {
    return baseClass;
  }

  public void setBaseClass(String baseClass) {
    this.baseClass = baseClass;
  }

  public String getClassNameSuffix() {
    return classNameSuffix;
  }

  public void setClassNameSuffix(String classNameSuffix) {
    this.classNameSuffix = classNameSuffix;
  }
}
