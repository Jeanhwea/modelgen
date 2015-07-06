package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.java.Customizer;
import ee.icefire.modelgen.java.model.JavaModel;

import java.util.ArrayList;
import java.util.List;

public class JpaModel extends JavaModel {

    protected List<Entity> entities = new ArrayList<Entity>();

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity findEntity(String entityName) {
        for (Entity entity : getEntities()) {
            if (entity.getClassName().equals(entityName)) {
                return entity;
            }
        }
        throw new RuntimeException("Entity '" + entityName + "' not found");
    }

    public Entity entity(String entityName, Customizer<Entity> customizer) {
        Entity entity = findEntity(entityName);
        customizer.customize(entity);
        return entity;
    }
}
