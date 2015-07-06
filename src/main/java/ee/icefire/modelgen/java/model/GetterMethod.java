package ee.icefire.modelgen.java.model;

import ee.icefire.modelgen.jpa.model.AccessModifier;
import ee.icefire.modelgen.utils.StringUtils;

public class GetterMethod extends Method {

    protected Field field;
    
    public GetterMethod(Field field) {
        super();
        this.access = AccessModifier.PUBLIC;
        this.field = field;
    }

    @Override
    public String getName() {
        return deriveGetterName(field.getName());
    }

    @Override
    public String getReturnType() {
        return field.getType();
    }

    protected String generateBody() {
        return "return " + field.getName() + ";";
    }

    @Override
    public String getBody() {
        return body != null ? super.getBody() : generateBody();
    }
    
    public static String deriveGetterName(String name) {
        return "get" + StringUtils.capitalize(name);
    }    
}
