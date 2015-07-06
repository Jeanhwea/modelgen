package ee.icefire.modelgen.jpa.model;

public enum AssociationType {

    ONE_TO_MANY("OneToMany"),
    ONE_TO_ONE("OneToOne"),
    MANY_TO_ONE("ManyToOne"),
    MANY_TO_MANY("ManyToMany");

    private String name;

    private AssociationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static boolean isToMany(AssociationType associationType) {
        return associationType == ONE_TO_MANY || associationType == MANY_TO_MANY;
    }
}
