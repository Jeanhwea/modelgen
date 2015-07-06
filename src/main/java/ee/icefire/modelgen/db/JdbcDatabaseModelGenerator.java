package ee.icefire.modelgen.db;

import ee.icefire.modelgen.db.model.DatabaseModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Abstract implementation of DatabaseModelGenerator that 
 */
public abstract class JdbcDatabaseModelGenerator implements DatabaseModelGenerator {
    
    protected JdbcConfig jdbcConfig;
    protected DatabaseModelGeneratorConfig generatorConfig;
    protected DatabaseModelFilter filter;

    public JdbcDatabaseModelGenerator(JdbcConfig jdbcConfig, DatabaseModelGeneratorConfig generatorConfig) {
        this.jdbcConfig = jdbcConfig;
        this.generatorConfig = generatorConfig;
        this.filter = new DatabaseModelFilter(generatorConfig);
    }

    public void configureJdbcProperties(Properties properties) {
        // noop
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcConfig.getDriverClassName());
        Properties properties = new Properties();
        properties.put("user", jdbcConfig.getJdbcUser());
        properties.put("password", jdbcConfig.getJdbcPassword());
        configureJdbcProperties(properties);
        return DriverManager.getConnection(jdbcConfig.getJdbcUrl(), properties);
    }
    
    public DatabaseModel generate() throws Exception {
        Connection connection = getConnection();
        DatabaseModel databaseModel;
        try {
            databaseModel = generate(connection);
            customizeModel(databaseModel, connection);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return databaseModel;
    }
    
    protected void customizeModel(DatabaseModel databaseModel, Connection connection) {
        for (DatabaseModelCustomizer customizer : generatorConfig.getCustomizers()) {
            customizer.customize(databaseModel, connection);
        }
    }
    
    abstract public DatabaseModel generate(Connection connection);

    public JdbcConfig getJdbcConfig() {
        return jdbcConfig;
    }

    public DatabaseModelGeneratorConfig getGeneratorConfig() {
        return generatorConfig;
    }
}
