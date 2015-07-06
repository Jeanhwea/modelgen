package ee.icefire.modelgen.jpa.model;

public enum AccessModifier {

    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PACKAGE("package");

    private String name;

    private AccessModifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
