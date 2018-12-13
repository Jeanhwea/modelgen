package ee.icefire.modelgen.java.model;

import ee.icefire.modelgen.jpa.model.AccessModifier;
import ee.icefire.modelgen.utils.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Method implements Annotated, Serializable {

  public static class Argument {

    protected String type;
    protected String name;

    public Argument(String type, String name) {
      this.type = type;
      this.name = name;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  protected AccessModifier access;
  protected boolean abstractModifier;
  protected String returnType = null;
  protected String name;
  protected List<Argument> arguments = new ArrayList<>();
  protected String body;
  protected List<Annotation> annotations = new ArrayList<>();
  protected List<String> exceptionTypes = new ArrayList<>();
  protected List<String> comments = new ArrayList<String>();

  protected Method() {
    // noop
  }

  public Method(AccessModifier access, String returnType, String name) {
    this.access = access;
    this.returnType = returnType;
    this.name = name;
  }

  public AccessModifier getAccess() {
    return access;
  }

  public boolean isAbstract() {
    return abstractModifier;
  }

  public void setAbstract(boolean abstractModifier) {
    this.abstractModifier = abstractModifier;
  }

  public String getReturnType() {
    return returnType;
  }

  public String getName() {
    return name;
  }

  public List<Argument> getArguments() {
    return arguments;
  }

  public Method argument(String type, String name) {
    this.getArguments().add(new Argument(type, name));
    return this;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Method body(String body) {
    setBody(body);
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

  public Method annotate(String annotation) {
    this.addAnnotation(new Annotation(annotation));
    return this;
  }

  public Method annotate(Annotation annotation) {
    this.addAnnotation(annotation);
    return this;
  }

  public List<String> getExceptionTypes() {
    return exceptionTypes;
  }

  public Method throwing(String exception) {
    this.exceptionTypes.add(exception);
    return this;
  }

  public List<String> getComments() {
    return comments;
  }
}
