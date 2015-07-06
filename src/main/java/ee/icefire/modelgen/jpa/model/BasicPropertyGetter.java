package ee.icefire.modelgen.jpa.model;


import ee.icefire.modelgen.java.model.Annotation;
import ee.icefire.modelgen.java.model.GetterMethod;

import java.util.List;

import static java.util.Collections.emptyList;

public class BasicPropertyGetter extends GetterMethod {

    protected BasicProperty basicProperty;

    public BasicPropertyGetter(BasicProperty basicProperty) {
        super(basicProperty);
        this.basicProperty = basicProperty;
    }

    @Override
    protected List<Annotation> generateAnnotations() {
        return basicProperty.getEntity().isAnnotationsOnGetters() ? basicProperty.generateJpaAnnotations() : emptyList();
    }
}
