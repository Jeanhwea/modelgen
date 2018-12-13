package ee.icefire.modelgen.jpa;

import ee.icefire.modelgen.jpa.model.*;
import ee.icefire.modelgen.db.model.Column;


public class AssociationHelper {

    protected JpaModel jpaModel;
    protected JpaEntityGeneratorConfig config;

    public AssociationHelper(JpaModel jpaModel, JpaEntityGeneratorConfig config) {
        this.jpaModel = jpaModel;
        this.config = config;
    }

    public AssociationProperty addOneToMany(String from, String to, AssociationProperty mappedBy) {
        Entity parent = jpaModel.findEntity(from);
        Entity child = jpaModel.findEntity(to);

        AssociationType associationType = AssociationType.ONE_TO_MANY;
        String name = config.getNamingConvention().associationName(child, associationType);
        return parent.createAssociation(associationType, child, name).mappedBy(mappedBy);
    }

    public AssociationProperty addManyToOne(String source, String target, String joinColumnName) {
        return addManyToOne(source, target, joinColumnName, null);
    }

    public AssociationProperty addManyToOne(String from, String to, String joinColumnName, String referencedColumnName) {
        Entity parent = jpaModel.findEntity(to);
        Entity child = jpaModel.findEntity(from);

        Column joinColumn = child.getMappedTable().findColumn(joinColumnName);
        child.removeProperty(child.findBasicProperty(joinColumn));

        Column referencedColumn = null;
        if (referencedColumnName != null) {
            referencedColumn = parent.getMappedTable().findColumn(referencedColumnName);
        }

        AssociationType associationType = AssociationType.MANY_TO_ONE;
        String name = config.getNamingConvention().associationName(parent, associationType);
        return child.createAssociation(associationType, parent, name).joinColumn(joinColumn, referencedColumn);
    }

    public Association addOneToManyBoth(String source, String target, String joinColumnName) {
        return addOneToManyBoth(source, target, joinColumnName, null);
    }

    public Association addOneToManyBoth(String from, String to, String joinColumnName, String referencedColumnName) {
        AssociationProperty owner = addManyToOne(to, from, joinColumnName, referencedColumnName);
        AssociationProperty inverse = addOneToMany(from, to, owner);
        return new Association(inverse, owner);
    }

    public AssociationProperty addOneToOne(String from, String to, String joinColumnName) {
        return addOneToOne(from, to, joinColumnName, null);
    }

    public AssociationProperty addOneToOne(String source, String target, String joinColumnName, String referencedColumnName) {
        Entity owner = jpaModel.findEntity(source);
        Entity inverse = jpaModel.findEntity(target);

        Column joinColumn = owner.getMappedTable().findColumn(joinColumnName);
        owner.removeProperty(owner.findBasicProperty(joinColumn));

        Column referencedColumn = null;
        if (referencedColumnName != null) {
            referencedColumn = inverse.getMappedTable().findColumn(referencedColumnName);
        }

        AssociationType associationType = AssociationType.ONE_TO_ONE;
        String name = config.getNamingConvention().associationName(inverse, associationType);
        return owner.createAssociation(associationType, inverse, name)
                .fetchType(FetchType.LAZY)
                .joinColumn(joinColumn, referencedColumn);
    }

    public AssociationProperty addOneToOneInverse(String source, String target, AssociationProperty mappedBy) {
        Entity inverse = jpaModel.findEntity(source);
        Entity owner = jpaModel.findEntity(target);

        AssociationType associationType = AssociationType.ONE_TO_ONE;
        String name = config.getNamingConvention().associationName(owner, associationType);
        return inverse.createAssociation(associationType, owner, name).mappedBy(mappedBy);
    }


    public Association addOneToOneBoth(String source, String target, String joinColumnName) {
        AssociationProperty owner = addOneToOne(source, target, joinColumnName, null);
        AssociationProperty inverse = addOneToOneInverse(target, source, owner);
        return new Association(owner, inverse);
    }

}
