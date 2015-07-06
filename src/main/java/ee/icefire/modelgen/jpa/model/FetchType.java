package ee.icefire.modelgen.jpa.model;

public enum FetchType {

    EAGER, LAZY;

    public String toJavaTypeString() {
        return "FetchType." + this.toString().toUpperCase();
    }
}
