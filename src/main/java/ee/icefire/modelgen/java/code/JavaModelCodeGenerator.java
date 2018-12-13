package ee.icefire.modelgen.java.code;

import ee.icefire.modelgen.jpa.model.Entity;
import ee.icefire.modelgen.jpa.model.JpaModel;

import java.io.IOException;

/**
 * Generates source code for a given Java model
 */
public class JavaModelCodeGenerator {

  protected JavaCodeGeneratorConfig config;
  protected JavaClassCodeGenerator classCodeGenerator;

  public JavaModelCodeGenerator(JavaCodeGeneratorConfig config) {
    this.config = config;
    this.classCodeGenerator = new JavaClassCodeGenerator(config);
  }

  public void generate(JpaModel jpaModel) throws IOException {
    for (Entity entity : jpaModel.getEntities()) {
      classCodeGenerator.generateClass(entity);
    }
  }

}
