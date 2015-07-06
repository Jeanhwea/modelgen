package ee.icefire.modelgen.java;

import ee.icefire.modelgen.utils.StringUtils;
import ee.icefire.modelgen.java.model.Field;
import ee.icefire.modelgen.java.model.JavaClass;

import java.util.Arrays;
import java.util.List;

public class ClassUtils {

    public static boolean isQualifiedClassName(String className) {
        return !extractPackage(className).isEmpty();
    }
    
    public static boolean isPackageName(String token) {
        return StringUtils.startsWithLower(token) || token.startsWith("_");
    }
    
    public static String extractClass(String qualifiedName) {
        String packageName = extractPackage(qualifiedName);
        if (packageName.isEmpty()) return qualifiedName;
        return qualifiedName.substring(packageName.length()+1);
    }

    public static String extractPackage(String qualifiedName) {
        String[] tokens = qualifiedName.split("\\.");
        String packageName = "";
        for (int i = 0; i < tokens.length; i++) {
            if (!isPackageName(tokens[i])) break;
            if (i > 0) packageName += ".";
            packageName += tokens[i];
        }
        return packageName;
    }

    public void unqualifyClassNames(JavaClass javaClass) {
        for (Field field : javaClass.getFields()) {

        }
    }

    public void optimizeImports(JavaClass javaClass) {
        //
    }
    
    public static final List<String> BASIC_TYPES = Arrays.asList("int", "long", "float", "double", "boolean", "char", "byte", "short");
    
    public static boolean isBasicType(String type) {
        return BASIC_TYPES.contains(type);
    }

}
