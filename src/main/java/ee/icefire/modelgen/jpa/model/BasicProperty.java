package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.db.model.Column;
import ee.icefire.modelgen.java.model.Annotation;
import ee.icefire.modelgen.java.model.GetterMethod;
import ee.icefire.modelgen.java.model.Property;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.*;

public class BasicProperty extends Property {

    protected Column mappedColumn;
    protected boolean primaryKey;
    protected GeneratedValue generatedValue;
    protected boolean insertable = true;
    protected boolean updatable = true;
    protected GetterMethod getter;
    protected Entity entity;

    public BasicProperty(Entity entity, String type, String name) {
        super(entity, type, name);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public Column getMappedColumn() {
        return mappedColumn;
    }

    public void setMappedColumn(Column mappedColumn) {
        this.mappedColumn = mappedColumn;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public GeneratedValue getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(GeneratedValue generatedValue) {
        this.generatedValue = generatedValue;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public BasicProperty insertable(boolean insertable) {
        setInsertable(insertable);
        return this;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public BasicProperty updatable(boolean updatable) {
        setUpdatable(updatable);
        return this;
    }
    
    public List<String> getComments() {
        return comments;
    }

    @Override
    public GetterMethod getGetter() {
        if (getter == null) {
            getter = new BasicPropertyGetter(this);
        }
        return this.getter;
    }

    protected List<Annotation> generateJpaAnnotations() {
        List<Annotation> annotations = new ArrayList<>(super.generateAnnotations());
        if (isPrimaryKey()) {
            annotations.add(new Annotation("Id"));
        }
        GeneratedValue generatedValue = getGeneratedValue();        
        if (generatedValue != null) {
            String strategy = "";
            if (generatedValue.isSequenceStrategy()) {
                strategy = "GenerationType.SEQUENCE";
            }
            annotations.add(new Annotation(format("GeneratedValue(strategy = %s, generator = \"%s\")", strategy, generatedValue.getGenerator())));
            if (generatedValue.isSequenceStrategy()) {
                String allocationSize = "";
                if (generatedValue.getSequenceAllocationSize() != null) {
                    allocationSize = ", allocationSize = " + generatedValue.getSequenceAllocationSize();
                }
                annotations.add(new Annotation(format("SequenceGenerator(name = \"%s\", sequenceName = \"%s\"%s)",
                        generatedValue.getGenerator(), generatedValue.getSequenceName(), allocationSize)));
            }
        }
        String insertable = isInsertable() ? "" : ", insertable = false";
        String updatable = isUpdatable() ? "" : ", updatable = false";
        
        annotations.add(new Annotation(format("Column(name = \"%s\"%s%s)", getMappedColumn().getName(), insertable, updatable)));
        return annotations;
    }

    @Override
    protected List<Annotation> generateAnnotations() {
        return !entity.isAnnotationsOnGetters() ? generateJpaAnnotations() : emptyList();
    }    
}
