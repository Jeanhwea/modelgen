package ee.icefire.modelgen.java.code;

import ee.icefire.modelgen.java.model.*;
import ee.icefire.modelgen.utils.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

public class JavaClassCodeGenerator {

    protected JavaCodeGeneratorConfig config;

    public JavaClassCodeGenerator(JavaCodeGeneratorConfig config) {
        this.config = config;
    }

    public void generateClass(final JavaClass javaClass) throws IOException {
        try (
                OutputStreamWriter streamWriter = new OutputStreamWriter(getOutputStream(javaClass), Charset.forName("UTF-8"));
                PrintWriter printWriter = new PrintWriter(streamWriter);
                
                ) {
            CodeWriter writer = new CodeWriter(printWriter, config.getCodeFormattingConfig());
            writePackage(writer, javaClass);
            writeImports(writer, javaClass);
            writeStaticImports(writer, javaClass);
            writeClassStart(writer, javaClass);
            writeClassStaticFields(writer, javaClass);
            writeClassPropertyFields(writer, javaClass);
            writeClassFields(writer, javaClass);
            writeClassConstructors(writer, javaClass);
            writeClassPropertyGettersSetters(writer, javaClass);
            writeClassMethods(writer, javaClass);
            writeClassEnd(writer, javaClass);
            writer.flush();
        }
    }

    protected String getPackageDirectory(final JavaClass javaClass) {
        return javaClass.getPackage().getName().replace(".", "/");
    }

    protected void ensurePackageDirectory(String directory) {
        new File(directory).mkdirs();
    }

    protected OutputStream getOutputStream(final JavaClass javaClass) throws FileNotFoundException {
        String directory = config.getDirectory() + getPackageDirectory(javaClass) + File.separator;
        ensurePackageDirectory(directory);
        File file = new File(directory + javaClass.getClassName() + ".java");
        return new FileOutputStream(file);
    }

    protected void writePackage(final CodeWriter writer, final JavaClass javaClass) {
        writer.format("package %s;\n", javaClass.getPackage().getName());
        writer.println();
    }

    protected void writeImports(final CodeWriter writer, final JavaClass javaClass) {
        for (String namespace : javaClass.getImports()) {
            writer.format("import %s;\n", namespace);
        }
        writer.println();
    }

    protected void writeStaticImports(final CodeWriter writer, final JavaClass javaClass) {
        for (String namespace : javaClass.getStaticImports()) {
            writer.format("import static %s;\n", namespace);
        }
        writer.println();
    }

    protected void writeClassStart(final CodeWriter writer, final JavaClass javaClass) {
        if (config.isIncludeClassComments()) {
            if (!javaClass.getComments().isEmpty()) {
                writer.format("/**\n");
                for (String comment : javaClass.getComments()) {
                    writer.format(" *  %s\n", comment);
                }
                writer.format(" */\n");
            }
        }
        for (Annotation annotation : javaClass.getAnnotations()) {
            writeAnnotation(writer, annotation);
        }
        writer.format("public class %s", javaClass.getClassName());
        if (javaClass.getBaseClassName() != null) {
            writer.print(" extends ").print(javaClass.getBaseClassName());
        }
        if (javaClass.getInterfaces().size() > 0) {
            writer.print(" implements ").print(StringUtils.join(javaClass.getInterfaces(), ", "));
        }
        
        writer.blockStart();
        writer.println();
    }
    
    protected void writeClassStaticFields(final CodeWriter writer, final JavaClass javaClass) {
        for (Field field : javaClass.getFields()) {
            if (field.isStatic()) writeField(writer, field);
        }
    }
    
    protected void writeClassFields(final CodeWriter writer, final JavaClass javaClass) {
        for (Field field : javaClass.getFields()) {
            if (!field.isStatic()) writeField(writer, field);
        }
    }
    
    protected void writeClassPropertyFields(final CodeWriter writer, final JavaClass javaClass) {
        for (Property property : javaClass.getProperties()) {
            if (!property.isComputed()) writeField(writer, property);
        }
    }
    
    protected void writeField(final CodeWriter writer, Field field) {
        for (String comment : field.getComments()) {
            writer.indent().print("// ").print(comment).eol();
        }
        for (Annotation annotation : field.getAnnotations()) {
            writeAnnotation(writer, annotation);
        }
        writer.indent();
        if (field.isStatic()) {
            writer.print("static ");
        }
        if (field.isFinal()) {
            writer.print("final ");
        }
        writer.format("%s %s", field.getType(), field.getName());
        if (field.getDefaultValue() != null) {
            writer.format(" = %s", field.getDefaultValue());
        }
        writer.eosl().indent().eol();
    }

    protected void writeClassConstructors(final CodeWriter writer, JavaClass javaClass) {
        for (Constructor constructor : javaClass.getConstructors()) {
            writeMethod(writer, constructor);
        }
    }

    protected void writeClassPropertyGettersSetters(final CodeWriter writer, JavaClass javaClass) {
        for (Property property : javaClass.getProperties()) {
            writeFieldGetterSetter(writer, property);
        }
    }

    protected void writeClassMethods(final CodeWriter writer, JavaClass javaClass) {
        for (Method method : javaClass.getMethods()) {
            writeMethod(writer, method);
        }
    }

    protected void writeFieldGetterSetter(final CodeWriter writer, Property prop) {
        if (prop.isReadable()) {
            writeMethod(writer, prop.getGetter());
        }
        if (prop.isWritable()) {
            writeMethod(writer, prop.getSetter());
        }
    }

    protected void writeMethod(final CodeWriter writer, Method method) {
        for (String comment : method.getComments()) {
            writer.indent().print("// ").print(comment).eol();
        }
        for (Annotation annotation : method.getAnnotations()) {
            writeAnnotation(writer, annotation);
        }
        writer.format("    %s %s %s(", method.getAccess().getName(), method.getReturnType() != null ? method.getReturnType() : "void", method.getName());
        boolean isFirst = true;
        for (Method.Argument argument : method.getArguments()) {
            if (isFirst) isFirst = false; else writer.print(", ");
            writer.format("%s %s", argument.getType(), argument.getName());
        }
        writer.print(")");
        if (method.getExceptionTypes().size() > 0) {
            writer.print(" throws ").print(StringUtils.join(method.getExceptionTypes(), ", "));
        }
        writer.blockStart();
        if (method.getBody() != null) {
            String[] lines = method.getBody().split("\n");
            for (String line : lines) {
                writer.indent().print(line).eol();
            }
        }
        writer.blockEnd().eol().eol();
    }

    protected void writeAnnotation(final CodeWriter writer, Annotation annotation) {
        writer.indent().print("@").print(annotation.getName());
        if (annotation.hasAttributes()) {
            writer.print("(");
            boolean isFirst = true;
            for (Annotation.Attribute attr : annotation.getAttributes()) {
                if (isFirst) isFirst = false; else writer.print(", ");
                writer.print(attr.getName()).print("=").print(attr.getValue());
            }
            writer.print(")");
        }
        writer.eol();
    }
    
    protected void writeClassEnd(final CodeWriter writer, JavaClass javaClass) {
        writer.blockEnd().eol();
    }

}
