package ee.icefire.modelgen.jpa.mapping;

import ee.icefire.modelgen.db.model.*;
import ee.icefire.modelgen.jpa.model.*;
import ee.icefire.modelgen.jpa.JpaEntityGeneratorConfig;

import static ee.icefire.modelgen.java.ClassUtils.extractClass;
import static ee.icefire.modelgen.java.ClassUtils.extractPackage;

public class DatabaseToJpaModelConverter {

    protected JpaEntityGeneratorConfig config;

    protected NamingConvention namingConvention;
    protected TypeConvention typeConvention;

    public DatabaseToJpaModelConverter(JpaEntityGeneratorConfig config) {
        this.config = config;
        initialize();
    }

    protected void initialize() {
        namingConvention = config.getNamingConvention();
        if (namingConvention == null) namingConvention = new DefaultNamingConvention(config);

        typeConvention = config.getTypeConvention();
        if (typeConvention == null) typeConvention = new DefaultTypeConvention();
    }

    public JpaModel convert(DatabaseModel databaseModel) {
        JpaModel jpaModel = new JpaModel();
        jpaModel.addPackage(config.getBasePackage());
        for (Schema schema : databaseModel.getSchemas()) {
            for (Table table : schema.getTables()) {
                Entity entity = convertTableToEntity(jpaModel, table);
                if (entity != null) {
                    jpaModel.getEntities().add(entity);
                }
            }
        }
        return jpaModel;
    }

    protected String potentialSequenceName(Column column) {
        if (this.config.getSequenceNameOverrides() != null) {
            if (this.config.getSequenceNameOverrides().containsKey(column.getTable().getName())) {
                return this.config.getSequenceNameOverrides().get(column.getTable().getName());
            }
        }
        return config.getSequencePrefix() + column.getTable().getName() + config.getSequenceSuffix();
    }

    protected Entity convertTableToEntity(JpaModel jpaModel, Table table) {
        Entity entity = new Entity();
        entity.setMappedTable(table);
        entity.setClassName(config.getNamingConvention().entityName(table));
        entity.setPackage(jpaModel.findPackage(config.getBasePackage()));
        entity.getComments().add(table.getRemarks());
        
        if (config.getEntitiesBaseClass() != null) {
            entity.setBaseClassName(config.getEntitiesBaseClass());
            entity.imports(config.getEntitiesBaseClassImport());
        }
        
        entity.setAnnotationsOnGetters(config.isPlaceAnnotationsOnGetters());
        
        if (config.isAddGeneratedAnnotation()) {
            entity.imports("javax.annotation.Generated").annotate("Generated(\"jpa-entity-generator\")");
        }
        
        entity.imports("javax.persistence.*");

        // Import collections because they are ubiquitous
        entity.imports("java.util.*");

        if (config.isEntitiesImplementSerializable()) {
            entity.imports("java.io.Serializable").implement("Serializable");
        }

        if (config.isGenerateToStringMethod()) {
            entity.getMethods().add(new ToStringMethod(entity));
        }

        for (Column column : table.getColumns()) {
            convertColumnToProperty(entity, column);
        }

        return entity;
    }

    protected BasicProperty convertColumnToProperty(Entity entity, Column column) {
        String type = convertPropertyType(column);
        String name = namingConvention.propertyName(column);
        BasicProperty basicProperty = entity.createBasicProperty(column.getName(), extractClass(type), name);
        if (!extractPackage(type).isEmpty()) {
            entity.imports(type);
        }
        basicProperty.setPrimaryKey(column.isPrimaryKey());
        basicProperty.setNullable(column.isNullable());
        if (config.isPlaceCommentsOnGetters()) {
            basicProperty.getGetter().getComments().add(column.getRemarks());
        } else {
            basicProperty.getComments().add(column.getRemarks());
        }

        if (basicProperty.isPrimaryKey()) {
            Sequence sequence = entity.getMappedTable().getSchema().findSequence(potentialSequenceName(column));
            if (sequence != null) {
                GeneratedValue generatedValue = new GeneratedValue(column.getName());
                generatedValue.setSequenceStrategy(true);
                generatedValue.setSequenceName(sequence.getQualifiedName());
                if (config.getDefaultAllocationSize() != null) {
                    generatedValue.setSequenceAllocationSize(config.getDefaultAllocationSize());
                }
                basicProperty.setGeneratedValue(generatedValue);
            }
        }

        return basicProperty;
    }

    protected String convertPropertyType(Column column) {
        String type = typeConvention.typeForColumn(column);
        if (type == null) {
            throw new RuntimeException("Could not map type " + column.getTypeName() + " for column " + column);
        }
        return type;
    }

}
