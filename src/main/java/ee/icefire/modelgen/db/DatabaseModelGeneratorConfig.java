package ee.icefire.modelgen.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseModelGeneratorConfig {

  protected String includedSchemas = ".*";
  protected String includedTables = ".*";
  protected String includedColumns = ".*";
  protected String excludedSchemas = "";
  protected String excludedTables = "";
  protected String excludedColumns = "";
  protected String excludeViews = "";
  protected boolean excludeAllViews = false;

  protected List<DatabaseModelCustomizer> customizers = new ArrayList<>();

  public String getIncludedSchemas() {
    return includedSchemas;
  }

  public void setIncludedSchemas(String includedSchemas) {
    this.includedSchemas = includedSchemas;
  }

  public String getIncludedTables() {
    return includedTables;
  }

  public void setIncludedTables(String includedTables) {
    this.includedTables = includedTables;
  }

  public String getIncludedColumns() {
    return includedColumns;
  }

  public void setIncludedColumns(String includedColumns) {
    this.includedColumns = includedColumns;
  }

  public String getExcludedSchemas() {
    return excludedSchemas;
  }

  public void setExcludedSchemas(String excludedSchemas) {
    this.excludedSchemas = excludedSchemas;
  }

  public String getExcludedTables() {
    return excludedTables;
  }

  public void setExcludedTables(String excludedTables) {
    this.excludedTables = excludedTables;
  }

  public String getExcludedColumns() {
    return excludedColumns;
  }

  public void setExcludedColumns(String excludedColumns) {
    this.excludedColumns = excludedColumns;
  }

  public String getExcludedViews() {
    return excludeViews;
  }

  public void setExcludeViews(String excludeViews) {
    this.excludeViews = excludeViews;
  }

  public boolean isExcludeAllViews() {
    return excludeAllViews;
  }

  public void setExcludeAllViews(boolean excludeAllViews) {
    this.excludeAllViews = excludeAllViews;
  }

  public List<DatabaseModelCustomizer> getCustomizers() {
    return Collections.unmodifiableList(customizers);
  }

  public void addCustomizer(DatabaseModelCustomizer customizer) {
    customizers.add(customizer);
  }

  public void removeCustomizer(DatabaseModelCustomizer customizer) {
    customizers.remove(customizer);
  }

  public boolean containsCustomizer(DatabaseModelCustomizer customizer) {
    return customizers.contains(customizer);
  }

}
