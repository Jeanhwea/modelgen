package ee.icefire.modelgen.java.model;

import java.util.List;

public interface Annotated {
    
    public List<Annotation> getAnnotations();
    
    public void addAnnotation(Annotation annotation);
    
    public Object annotate(Annotation annotation);

    public Object annotate(String annotate);
    
}
