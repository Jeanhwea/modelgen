package ee.icefire.modelgen.db.oracle;


import ee.icefire.modelgen.db.DatabaseModelGeneratorConfig;
import ee.icefire.modelgen.db.JdbcConfig;
import ee.icefire.modelgen.db.JdbcDatabaseModelGenerator;
import ee.icefire.modelgen.db.model.*;
import oracle.jdbc.OracleDatabaseMetaData;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class OracleDatabaseModelGenerator extends JdbcDatabaseModelGenerator {
    
    public OracleDatabaseModelGenerator(JdbcConfig jdbcConfig, DatabaseModelGeneratorConfig modelGeneratorConfig) {
        super(jdbcConfig, modelGeneratorConfig);
    }

    @Override
    public void configureJdbcProperties(Properties properties) {
        // Enable remarks reporting for Oracle (this is disabled by default for performance reasons)
        properties.put("remarksReporting", "true");
    }

    @Override
    public DatabaseModel generate(Connection connection) {
        try {
            DatabaseModel databaseModel = new DatabaseModel();
            OracleDatabaseMetaData oracleDatabaseMetaData = (OracleDatabaseMetaData) connection.getMetaData();
            populateSchemas(oracleDatabaseMetaData, databaseModel);
            for (Schema schema : databaseModel.getSchemas()) {
                populateSequences(connection, schema);
                populateTables(oracleDatabaseMetaData, schema);
                populateColumns(oracleDatabaseMetaData, schema.getTables());
                populatePrimaryKeys(oracleDatabaseMetaData, schema.getTables());
                populateForeignKeys(oracleDatabaseMetaData, databaseModel, schema.getTables());
            }
            return databaseModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateSequences(Connection connection, Schema schema) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select sequence_name from all_sequences where sequence_owner = ?");
            preparedStatement.setString(1, schema.getName());
            ResultSet rs = preparedStatement.executeQuery();
            try {
                while (rs.next()) {
                    schema.getSequences().add(new Sequence(schema, rs.getString("SEQUENCE_NAME")));
                }
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void populateSchemas(OracleDatabaseMetaData databaseMetaData, DatabaseModel databaseModel) throws SQLException {
        ResultSet rs = databaseMetaData.getSchemas();
        try {
            while (rs.next()) {
                Schema schema = new Schema(rs.getString("TABLE_SCHEM"));
                if (filter.acceptSchema(schema)) {
                    databaseModel.getSchemas().add(schema);
                }
            }
        } finally {
            rs.close();
        }
    }

    protected void populateTables(OracleDatabaseMetaData databaseMetaData, Schema schema) throws SQLException {
        ResultSet rs = databaseMetaData.getTables(null, schema.getName(), null, null);
        try {
            while (rs.next()) {
                Table table = new Table(schema);
                table.setName(rs.getString("TABLE_NAME"));
                table.setRemarks(rs.getString("REMARKS"));

                if ("null".equals(table.getRemarks())) {
                    table.setRemarks(null);
                }

                table.setView("VIEW".equals(rs.getString("TABLE_TYPE")));
                if (filter.acceptTable(table)) {
                    schema.getTables().add(table);
                }
            }
        } finally {
            rs.close();
        }
    }

    private void populateColumns(OracleDatabaseMetaData databaseMetaData, List<Table> tables) throws SQLException {
        for (Table table : tables) {
            ResultSet rs = databaseMetaData.getColumns(null, table.getSchema().getName(), table.getName(), "%");
            try {
                while (rs.next()) {
                    Column column = new Column(table);
                    column.setName(rs.getString("COLUMN_NAME"));

                    column.setType(rs.getInt("DATA_TYPE"));
                    column.setTypeName(rs.getString("TYPE_NAME"));
                    column.setNullable(rs.getInt("NULLABLE") != ResultSetMetaData.columnNoNulls);
                    column.setLength(rs.getInt("COLUMN_SIZE"));
                    column.setPrecision(rs.getInt("DECIMAL_DIGITS"));
                    column.setRemarks(rs.getString("REMARKS"));

                    if ("null".equals(column.getRemarks())) {
                        column.setRemarks(null);
                    }

                    fixColumnType(column);

                    if (filter.acceptColumn(column)) {
                        table.getColumns().add(column);
                    }
                }
            } finally {
                rs.close();
            }
        }
    }

    protected void fixColumnType(Column column) {
        if (column.getType() == Types.DECIMAL && column.getPrecision() == 0) {
            column.setType(Types.BIGINT);
        }
    }

    private void populatePrimaryKeys(OracleDatabaseMetaData databaseMetaData, List<Table> tables) throws SQLException {
        for (Table table : tables) {
            ResultSet rs = databaseMetaData.getPrimaryKeys(null, table.getSchema().getName(), table.getName());
            try {
                while (rs.next()) {
                    Column column = table.findColumn(rs.getString("COLUMN_NAME"));
                    column.setPrimaryKey(true);
                }
            } finally {
                rs.close();
            }
        }
    }

    private void populateForeignKeys(OracleDatabaseMetaData databaseMetaData, DatabaseModel databaseModel, List<Table> tables) throws SQLException {
        for (Table table : tables) {
            ResultSet rs = databaseMetaData.getImportedKeys(null, table.getSchema().getName(), table.getName());
            try {
                while (rs.next()) {
                    ForeignKey foreignKey = new ForeignKey();
                    Column foreignKeyColumn = table.findColumn(rs.getString("FKCOLUMN_NAME"));
                    Schema referencedSchema = databaseModel.findSchema(rs.getString("PKTABLE_SCHEM"));
                    Table referencedTable = referencedSchema.findTable(rs.getString("PKTABLE_NAME"));
                    Column referencedColumn = referencedTable.findColumn(rs.getString("PKCOLUMN_NAME"));
                    foreignKey.setColumn(foreignKeyColumn);
                    foreignKey.setReferenceColumn(referencedColumn);
                    table.getForeignKeys().add(foreignKey);

                }
            } finally {
                rs.close();
            }
        }
    }
}
