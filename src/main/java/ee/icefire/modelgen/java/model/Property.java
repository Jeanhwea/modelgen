package ee.icefire.modelgen.java.model;

/**
 * Represents a field with a getter and/or setter
 */
public class Property extends Field {

  protected boolean computed = false;
  protected boolean readable = true;
  protected boolean writable = true;
  private GetterMethod getter;
  private SetterMethod setter;

  public Property(JavaClass javaClass, String type, String name) {
    super(javaClass, type, name);
  }

  public Property defaultValue(String value) {
    super.defaultValue(value);
    return this;
  }

  public boolean isComputed() {
    return computed;
  }

  public void setComputed(boolean computed) {
    this.computed = computed;
  }

  public boolean isReadable() {
    return readable;
  }

  public void setReadable(boolean readable) {
    this.readable = readable;
  }

  public Property readable(boolean readable) {
    setReadable(readable);
    return this;
  }

  public boolean isWritable() {
    return writable;
  }

  public void setWritable(boolean writable) {
    this.writable = writable;
  }

  public Property writable(boolean writable) {
    setWritable(writable);
    return this;
  }

  public GetterMethod getGetter() {
    if (getter == null) {
      getter = new GetterMethod(this);
    }
    return getter;
  }

  public SetterMethod getSetter() {
    if (setter == null) {
      setter = new SetterMethod(this);
    }
    return setter;
  }

  public Property annotate(String annotation) {
    super.annotate(annotation);
    return this;
  }

  public Property annotate(Annotation annotation) {
    super.annotate(annotation);
    return this;
  }

  public Property annotateGetter(String annotation) {
    getGetter().annotate(annotation);
    return this;
  }

  public Property annotateSetter(String annotation) {
    getSetter().annotate(annotation);
    return this;
  }
}
