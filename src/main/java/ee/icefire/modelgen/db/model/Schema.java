package ee.icefire.modelgen.db.model;

import java.util.ArrayList;
import java.util.List;

public class Schema {

    protected String name;

    protected List<Table> tables = new ArrayList<>();
    protected List<Sequence> sequences = new ArrayList<>();

    public Schema(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table findTable(String tableName) {
        for (Table table : getTables()) {
            if (table.getName().equals(tableName)) {
                return table;
            }
        }
        return null;
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public Sequence findSequence(String sequenceName) {
        for (Sequence sequence : getSequences()) {
            if (sequence.getName().equals(sequenceName)) {
                return sequence;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
