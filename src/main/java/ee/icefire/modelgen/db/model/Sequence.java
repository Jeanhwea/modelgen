package ee.icefire.modelgen.db.model;

public class Sequence {

    protected Schema schema;

    protected String name;

    public Sequence(Schema schema, String name) {
        this.schema = schema;
        this.name = name;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedName() {
        if (getSchema() != null) {
            return getSchema().getName() + "." + getName();
        } else {
            return getName();
        }
    }
}
