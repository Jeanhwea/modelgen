package ee.icefire.modelgen.jpa;

import ee.icefire.modelgen.db.JdbcConfig;
import ee.icefire.modelgen.java.code.JavaCodeGeneratorConfig;
import ee.icefire.modelgen.jpa.mapping.DatabaseToJpaModelConverterConfig;
import ee.icefire.modelgen.db.DatabaseModelGeneratorConfig;

/**
 * Holds configurations of all the processes that take part in JPA entity generation
 */
public class JpaEntityGeneratorConfig extends DatabaseToJpaModelConverterConfig {

  protected JdbcConfig jdbcConfig = new JdbcConfig();
  protected DatabaseModelGeneratorConfig databaseModelGeneratorConfig = new DatabaseModelGeneratorConfig();
  protected JavaCodeGeneratorConfig javaCodeGeneratorConfig = new JavaCodeGeneratorConfig();

  public JdbcConfig getJdbcConfig() {
    return jdbcConfig;
  }

  public void setJdbcConfig(JdbcConfig jdbcConfig) {
    this.jdbcConfig = jdbcConfig;
  }

  public DatabaseModelGeneratorConfig getDatabaseModelGeneratorConfig() {
    return databaseModelGeneratorConfig;
  }

  public void setDatabaseModelGeneratorConfig(DatabaseModelGeneratorConfig databaseModelGeneratorConfig) {
    this.databaseModelGeneratorConfig = databaseModelGeneratorConfig;
  }

  public JavaCodeGeneratorConfig getJavaCodeGeneratorConfig() {
    return javaCodeGeneratorConfig;
  }

  public void setJavaCodeGeneratorConfig(JavaCodeGeneratorConfig javaCodeGeneratorConfig) {
    this.javaCodeGeneratorConfig = javaCodeGeneratorConfig;
  }

}
