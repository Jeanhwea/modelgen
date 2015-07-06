package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.java.model.Field;
import ee.icefire.modelgen.java.model.Method;
import ee.icefire.modelgen.java.model.Property;

public class ToStringMethod extends Method {

    protected Entity entity;

    public ToStringMethod(Entity entity) {
        super(AccessModifier.PUBLIC, "String", "toString");
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String getBody() {
        StringBuilder sb = new StringBuilder();

        sb.append("return \"").append(this.getEntity().getClassName()).append(" {\"\n");
        writeFields(sb);
        sb.append("        + \"}\";");

        return sb.toString();
    }

    protected void writeFields(StringBuilder sb) {
        boolean first = true;
        for (BasicProperty field : getEntity().getBasicProperties()) {
            writeField(sb, field, first);
            first = false;
        }
        for (Property field : getEntity().getTransientProperties()) {
            writeField(sb, field, first);
            first = false;
        }
    }

    protected void writeField(StringBuilder sb, Field field, boolean first) {
        sb.append("        + \"").append(first ? " " : ", ");
        sb.append(field.getName());
        sb.append("=");
        boolean quoted = "String".equals(field.getType());
        sb.append(quoted ? "'" : "").append("\" + ").append(field.getName());
        if (quoted) sb.append(" + \"'\"");
        sb.append("\n");
    }
}
