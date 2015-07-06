package ee.icefire.modelgen.java.model;

import java.util.ArrayList;
import java.util.List;

import static ee.icefire.modelgen.utils.StringUtils.isEmpty;

public class JavaModel {
    
    protected List<JavaPackage> packages = new ArrayList<>();
    
    public JavaPackage findPackage(String packageName) {
        if (isEmpty(packageName)) return JavaPackage.DEFAULT_PACKAGE;
        for (JavaPackage p : packages) {
            if (p.getName().equals(packageName)) return p;
        }
        return null;
    }
    
    public void addPackage(JavaPackage p) {
        this.packages.add(p);
    }

    public void addPackage(String p) {
        this.packages.add(new JavaPackage(p));
    }
    
}
