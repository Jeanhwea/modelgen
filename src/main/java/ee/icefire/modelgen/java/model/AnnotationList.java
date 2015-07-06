package ee.icefire.modelgen.java.model;

import java.util.ArrayList;
import java.util.Collection;

public class AnnotationList extends ArrayList<Annotation> {

    public void add(String annotation) {
        this.add(new Annotation(annotation));
    }

    public AnnotationList() {
    }

    public AnnotationList(Collection<? extends Annotation> c) {
        super(c);
    }

}
