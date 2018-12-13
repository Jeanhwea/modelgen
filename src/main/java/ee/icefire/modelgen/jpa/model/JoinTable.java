package ee.icefire.modelgen.jpa.model;

import ee.icefire.modelgen.db.model.Table;

import java.util.ArrayList;
import java.util.List;

public class JoinTable {

  Table table;

  List<JoinColumn> joinColumns = new ArrayList<>();
  List<JoinColumn> inverseJoinColumns = new ArrayList<>();

  public Table getTable() {
    return table;
  }

  public void setTable(Table table) {
    this.table = table;
  }

  public List<JoinColumn> getJoinColumns() {
    return joinColumns;
  }

  public void setJoinColumns(List<JoinColumn> joinColumns) {
    this.joinColumns = joinColumns;
  }

  public List<JoinColumn> getInverseJoinColumns() {
    return inverseJoinColumns;
  }

  public void setInverseJoinColumns(List<JoinColumn> inverseJoinColumns) {
    this.inverseJoinColumns = inverseJoinColumns;
  }
}
