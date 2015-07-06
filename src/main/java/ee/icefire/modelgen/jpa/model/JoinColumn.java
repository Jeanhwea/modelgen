package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.db.model.Column;

public class JoinColumn {

    protected AssociationProperty field;

    protected Column foreignKeyColumn;
    protected Column referencedColumn;

    protected boolean nullable = true;
    protected boolean updatable = true;
    protected boolean insertable = true;

    public JoinColumn(AssociationProperty field, Column foreignKeyColumn) {
        this.field = field;
        this.foreignKeyColumn = foreignKeyColumn;
    }

    public JoinColumn(AssociationProperty field, Column foreignKeyColumn, Column referencedColumn) {
        this.field = field;
        this.foreignKeyColumn = foreignKeyColumn;
        this.referencedColumn = referencedColumn;
    }

    public AssociationProperty getField() {
        return field;
    }

    public Column getForeignKeyColumn() {
        return foreignKeyColumn;
    }

    public void setForeignKeyColumn(Column foreignKeyColumn) {
        this.foreignKeyColumn = foreignKeyColumn;
    }

    public Column getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(Column referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }
}
