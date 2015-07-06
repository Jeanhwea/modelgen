package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.db.model.Table;
import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.java.model.Annotation;
import ee.icefire.modelgen.java.model.JavaClass;
import ee.icefire.modelgen.java.model.Property;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static ee.icefire.modelgen.java.model.Annotation.annotation;
import static ee.icefire.modelgen.utils.StringUtils.quot;

public class Entity extends JavaClass {

    protected Table mappedTable;

    protected boolean annotationsOnGetters;
    
    protected boolean cacheable;

    public Table getMappedTable() {
        return mappedTable;
    }

    public void setMappedTable(Table mappedTable) {
        this.mappedTable = mappedTable;
    }

    public List<BasicProperty> getBasicProperties() {
        return unmodifiableList(properties.stream()
                .filter(prop -> prop instanceof BasicProperty)
                .map(prop -> (BasicProperty) prop)
                .collect(toList()));
    }

    public List<Property> getTransientProperties() {
        return unmodifiableList(properties.stream()
                .filter(prop -> prop.getAnnotations().contains("Transient"))
                .collect(toList()));
    }

    public List<AssociationProperty> getAssociations() {
        return unmodifiableList(properties.stream()
                .filter(prop -> prop instanceof AssociationProperty)
                .map(prop -> (AssociationProperty) prop)
                .collect(toList()));
    }

    public boolean isAnnotationsOnGetters() {
        return annotationsOnGetters;
    }

    public void setAnnotationsOnGetters(boolean annotationsOnGetters) {
        this.annotationsOnGetters = annotationsOnGetters;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public BasicProperty findBasicProperty(Column column) {
        for (BasicProperty basicProperty : getBasicProperties()) {
            if (basicProperty.getMappedColumn() == column) {
                return basicProperty;
            }
        }
        return null;
    }

    public BasicProperty findBasicProperty(String name) {
        for (BasicProperty basicProperty : getBasicProperties()) {
            if (basicProperty.getName().equals(name)) {
                return basicProperty;
            }
        }
        return null;
    }

    public BasicProperty createBasicProperty(String columnName, String type, String name) {
        return createBasicProperty(columnName, type, name, null);
    }

    public BasicProperty createBasicProperty(String columnName, String type, String name, String defaultValue) {
        BasicProperty prop = new BasicProperty(this, type, name);
        prop.setDefaultValue(defaultValue);
        Column column = this.getMappedTable().findColumn(columnName);
        prop.setMappedColumn(column);
        this.properties.add(prop);
        return prop;
    }

    public Property createTransientProperty(String type, String name) {
        return createTransientProperty(type, name, null);
    }

    public Property createTransientProperty(String type, String name, String defaultValue) {
        return this.createProperty(type, name).defaultValue(defaultValue).annotate("Transient");
    }
    
    public Property findTransientProperty(String propertyName) {
        return findProperty(propertyName);
    }
    
    public AssociationProperty createAssociation(AssociationType associationType, Entity relatedEntity, String name) {
        AssociationProperty prop = new AssociationProperty(this, associationType, relatedEntity, name);
        this.properties.add(prop);
        return prop;
    }
    
    public void excludeSuperclass(Collection<String> propNames) {
        propNames.forEach(this::removeProperty);
    }
    
    public void excludeSuperclass(JavaClass superclass) {
        for (Property property : superclass.getProperties()) {
            this.removeProperty(property.getName());
        }
    }
    
    public Entity superclass(JavaClass superclass) {
        setBaseClassName(superclass.getClassName());
        excludeSuperclass(superclass);
        return this;
    }

    @Override
    protected List<Annotation> generateAnnotations() {
        ArrayList<Annotation> annotations = new ArrayList<>(super.generateAnnotations());
        annotations.add(annotation("Entity"));
        annotations.add(annotation("Table")
                .attr("name", quot(getMappedTable().getName()))
                .attr("schema", quot(getMappedTable().getSchema().getName()))
        );
        if (isCacheable()) {
            annotations.add(annotation("Cacheable"));
        }
        return annotations;
    }

    public String toString() {
        return "Entity " + getQualifiedClassName();
    }
}
