package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.java.model.Method;

public class EqualsMethod extends Method {

    protected Entity entity;

    public EqualsMethod(Entity entity) {
        super(AccessModifier.PUBLIC, "boolean", "equals");
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String getBody() {
        return null;
    }
}
