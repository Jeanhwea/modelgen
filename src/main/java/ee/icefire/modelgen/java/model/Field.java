package ee.icefire.modelgen.java.model;

import ee.icefire.modelgen.utils.CollectionUtils;
import ee.icefire.modelgen.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Field implements Annotated, Serializable {

  protected JavaClass javaClass;

  protected String name;
  protected String type;
  protected String defaultValue;

  protected boolean staticModifier;
  protected boolean finalModifier;
  protected boolean transientModifier;
  protected boolean volatileModifier;
  protected boolean nullable = true;

  protected List<String> comments = new ArrayList<String>();
  protected AnnotationList annotations = new AnnotationList();

  public Field(JavaClass javaClass, String type, String name) {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Field name can not be null or empty");
    }
    this.javaClass = javaClass;
    this.type = type;
    this.name = name;
  }

  public JavaClass getJavaClass() {
    return javaClass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Field name(String name) {
    setName(name);
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<String> getComments() {
    return comments;
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

  public Field annotate(String annotation) {
    this.addAnnotation(new Annotation(annotation));
    return this;
  }

  public Field annotate(Annotation annotation) {
    this.addAnnotation(annotation);
    return this;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public Field defaultValue(String value) {
    setDefaultValue(value);
    return this;
  }

  public boolean isStatic() {
    return staticModifier;
  }

  public void setStatic(boolean staticField) {
    this.staticModifier = staticField;
  }

  public boolean isFinal() {
    return finalModifier;
  }

  public void setFinal(boolean finalField) {
    this.finalModifier = finalField;
  }

  public boolean isTransient() {
    return transientModifier;
  }

  public void setTransient(boolean transientModifier) {
    this.transientModifier = transientModifier;
  }

  public boolean isVolatile() {
    return volatileModifier;
  }

  public void setVolatile(boolean volatileModifier) {
    this.volatileModifier = volatileModifier;
  }

  public boolean isNullable() {
    return nullable;
  }

  public void setNullable(boolean nullable) {
    this.nullable = nullable;
  }

  public String getQualifiedName() {
    return getJavaClass().getClassName() + "." + getName();
  }

}
