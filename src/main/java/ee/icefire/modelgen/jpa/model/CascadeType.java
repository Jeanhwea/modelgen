package ee.icefire.modelgen.jpa.model;

public enum CascadeType {

    /** Cascade all operations */
    ALL,

    /** Cascade persist operation */
    PERSIST,

    /** Cascade merge operation */
    MERGE,

    /** Cascade remove operation */
    REMOVE,

    /** Cascade refresh operation */
    REFRESH,

    /**
     * Cascade detach operation
     *
     * @since Java Persistence 2.0
     *
     */
    DETACH,

    PERSIST_MERGE;

    public String toJavaTypeString() {
        if (this == PERSIST_MERGE) {
            return "{ " + PERSIST.toJavaTypeString() + ", " + MERGE.toJavaTypeString() + " }";
        }
        return "CascadeType." + this;
    }
}
