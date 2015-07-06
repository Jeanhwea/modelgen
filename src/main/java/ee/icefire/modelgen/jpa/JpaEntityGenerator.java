package ee.icefire.modelgen.jpa;

import ee.icefire.modelgen.db.DatabaseModelGenerator;
import ee.icefire.modelgen.db.model.DatabaseModel;
import ee.icefire.modelgen.db.oracle.OracleDatabaseModelGenerator;
import ee.icefire.modelgen.java.code.JavaModelCodeGenerator;
import ee.icefire.modelgen.jpa.mapping.DatabaseToJpaModelConverter;
import ee.icefire.modelgen.jpa.model.JpaModel;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Highly customizable generator of JPA entities for Oracle database
 */
public class JpaEntityGenerator {

    protected JpaEntityGeneratorConfig config;

    protected DatabaseModelGenerator databaseModelGenerator;
    protected DatabaseToJpaModelConverter databaseToJpaModelConverter;
    protected JavaModelCodeGenerator javaModelCodeGenerator;

    public JpaEntityGenerator(JpaEntityGeneratorConfig config) {
        this.config = config;
        configure(config);
    }

    protected void configure(JpaEntityGeneratorConfig config) {
        this.databaseModelGenerator = new OracleDatabaseModelGenerator(config.getJdbcConfig(), config.getDatabaseModelGeneratorConfig());
        this.databaseToJpaModelConverter = new DatabaseToJpaModelConverter(config);
        this.javaModelCodeGenerator = new JavaModelCodeGenerator(config.getJavaCodeGeneratorConfig());
    }

    public JpaModel generate() throws Exception {
        DatabaseModel databaseModel = generateDatabaseModel();
        JpaModel jpaModel = convertDatabaseToJpaModel(databaseModel);
        generateJavaCode(jpaModel);
        return jpaModel;
    }

    protected DatabaseModel generateDatabaseModel() throws Exception {
        return databaseModelGenerator.generate();
    }

    protected JpaModel convertDatabaseToJpaModel(DatabaseModel databaseModel) {
        JpaModel jpaModel = databaseToJpaModelConverter.convert(databaseModel);
        if (config.getJpaModelCustomizer() != null) {
            config.getJpaModelCustomizer().customize(jpaModel);
        }
        return jpaModel;
    }

    protected void generateJavaCode(JpaModel jpaModel) throws IOException {
        try {
            javaModelCodeGenerator.generate(jpaModel);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}
