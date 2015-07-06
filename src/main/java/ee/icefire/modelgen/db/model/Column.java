package ee.icefire.modelgen.db.model;

public class Column {

    protected Table table;

    protected String name;
    protected int length;
    protected int precision;
    protected String defaultValue;
    protected boolean nullable;
    protected boolean primaryKey;
    protected String typeName;
    protected int type;
    protected String remarks;

    public Column(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getQualifiedName() {
        return getTable().getQualifiedName() + "." + getName();
    }

    @Override
    public String toString() {
        return getQualifiedName();
    }
}
