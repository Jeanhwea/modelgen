package ee.icefire.modelgen.db;

import ee.icefire.modelgen.db.model.Table;
import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.db.model.Schema;

import java.util.regex.Pattern;

public class DatabaseModelFilter {

    protected DatabaseModelGeneratorConfig config;

    protected Pattern includedSchemas;
    protected Pattern includedTables;
    protected Pattern includedColumns;
    protected Pattern excludedSchemas;
    protected Pattern excludedTables;
    protected Pattern excludedColumns;
    protected Pattern excludedViews;

    public static Pattern compilePattern(String regex) {
        return regex != null ? Pattern.compile(regex) : null;
    }
    
    public DatabaseModelFilter(DatabaseModelGeneratorConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Null configuration was passed");
        }
        this.config = config;
        
        compilePatterns();
    }
    
    public void compilePatterns() {
        includedSchemas = compilePattern(config.getIncludedSchemas());
        includedTables = compilePattern(config.getIncludedTables());
        includedColumns = compilePattern(config.getIncludedTables());
        excludedSchemas = compilePattern(config.getExcludedSchemas());
        excludedTables = compilePattern(config.getExcludedTables());
        excludedColumns = compilePattern(config.getExcludedTables());
        excludedViews = compilePattern(config.getExcludedViews());
    }

    public boolean acceptSchema(Schema schema) {
        return isSchemaIncluded(schema) && !isSchemaExcluded(schema);
    }

    public boolean acceptTable(Table table) {
        if (table.isView() && (config.isExcludeAllViews() || isViewExcluded(table))) {
            return false;
        }
        return isTableIncluded(table) && !isTableExcluded(table);
    }

    public boolean acceptColumn(Column column) {
        return isColumnIncluded(column) && !isColumnsExcluded(column);
    }    
    
    protected boolean isSchemaExcluded(Schema schema) {
        return excludedSchemas != null && excludedSchemas.matcher(schema.getName()).matches();
    }

    protected boolean isSchemaIncluded(Schema schema) {
        return includedSchemas == null || includedSchemas.matcher(schema.getName()).matches();
    }

    protected boolean isTableExcluded(Table table) {
        return excludedTables != null && excludedTables.matcher(table.getName()).matches();
    }

    protected boolean isTableIncluded(Table table) {
        return includedTables == null || includedTables.matcher(table.getName()).matches();
    }

    protected boolean isColumnsExcluded(Column column) {
        return excludedColumns != null && excludedColumns.matcher(column.getName()).matches();
    }

    protected boolean isColumnIncluded(Column column) {
        return includedColumns == null || includedColumns.matcher(column.getName()).matches();
    }

    protected boolean isViewExcluded(Table table) {
        return excludedViews != null && excludedViews.matcher(table.getName()).matches();
    }
}
