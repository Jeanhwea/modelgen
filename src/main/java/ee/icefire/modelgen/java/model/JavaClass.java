package ee.icefire.modelgen.java.model;

import ee.icefire.modelgen.jpa.model.AccessModifier;
import ee.icefire.modelgen.utils.CollectionUtils;

import java.io.Serializable;
import java.util.*;

public class JavaClass implements Annotated, Serializable {

    protected JavaPackage _package = JavaPackage.DEFAULT_PACKAGE;
    protected String className;
    protected String baseClassName;
    protected boolean publicAccess = true;

    protected Set<String> interfaces = new LinkedHashSet<String>();
    protected Set<String> imports = new LinkedHashSet<String>();
    protected Set<String> staticImports = new LinkedHashSet<>();
    protected List<String> comments = new ArrayList<String>();
    protected List<Annotation> annotations = new ArrayList<>();
    protected List<Field> fields = new ArrayList<Field>();
    protected List<Property> properties = new ArrayList<>();
    protected List<Method> methods = new ArrayList<Method>();
    protected List<Constructor> constructors = new ArrayList<>();

    public JavaClass() {
    }

    public JavaClass(JavaPackage _package) {
        setPackage(_package);
    }

    public JavaClass(String name) {
        setClassName(name);
    }

    public JavaClass(JavaPackage _package, String name) {
        setPackage(_package);
        setClassName(name);
    }    

    public JavaPackage getPackage() {
        return _package;
    }

    public void setPackage(JavaPackage _package) {
        this._package = _package;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Set<String> getImports() {
        return imports;
    }

    public Set<String> getStaticImports() {
        return staticImports;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public JavaClass imports(String namespace) {
        if (!namespace.startsWith("java.lang.")) {
            this.imports.add(namespace);
        }
        return this;
    }
    
    public JavaClass imports(Class<?> clazz) {
        imports(clazz.getCanonicalName());
        return this;
    }

    public JavaClass staticallyImports(String classOrMethod) {
        this.staticImports.add(classOrMethod);
        return this;
    }

    public JavaClass extend(String clazz) {
        imports(clazz);
        setBaseClassName(clazz);
        return this;
    }

    public JavaClass implement(String iface) {
        getInterfaces().add(iface);
        return this;
    }

    protected List<Annotation> generateAnnotations() {
        return Collections.emptyList();
    }
    
    public List<Annotation> getAnnotations() {
        return CollectionUtils.unmodifiableMerge(generateAnnotations(), annotations);
    }
    
    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }
    
    public JavaClass annotate(String annotation) {
        this.addAnnotation(new Annotation(annotation));
        return this;
    }
    
    public JavaClass annotate(Annotation annotation) {
        this.addAnnotation(annotation);
        return this;
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public Field findField(String fieldName) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    public boolean containsField(String fieldName) {
        return findField(fieldName) != null;
    }

    public boolean removeField(Field field) {
        return fields.remove(field);
    }

    public boolean removeField(String fieldName) {
        return fields.remove(findField(fieldName));
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public Property createProperty(String type, String name) {
        return createProperty(type, name, null);
    }
    
    public Property createProperty(String type, String name, String defaultValue) {
        Property prop = new Property(this, type, name);
        prop.setDefaultValue(defaultValue);
        properties.add(prop);
        return prop;
    }

    public Property findProperty(String propertyName) {
        for (Property property : properties) {
            if (property.getName().equals(propertyName)) {
                return property;
            }
        }
        return null;

    }

    public boolean containsProperty(String propertyName) {
        return findProperty(propertyName) != null;
    }
    
    public boolean removeProperty(Property property) {
        if (property == null) return false;
        return properties.remove(property);
    }

    public boolean removeProperty(String propertyName) {
        return properties.remove(findProperty(propertyName));
    }

    public List<Method> getMethods() {
        return methods;
    }

    public Method method(String name) {
        for (Method method : getMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public Method createMethod(AccessModifier accessModifier, String returnType, String name) {
        return createMethod(new Method(accessModifier, returnType, name));
    }

    public Method createMethod(Method method) {
        this.methods.add(method);
        return method;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    public List<String> getComments() {
        return comments;
    }

    public String getQualifiedClassName() {
        String prefix = getPackage() != JavaPackage.DEFAULT_PACKAGE ? getPackage().getName() : "";
        return prefix + "." + getClassName();
    }

}
