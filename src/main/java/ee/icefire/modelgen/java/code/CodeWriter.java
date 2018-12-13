package ee.icefire.modelgen.java.code;

import java.io.PrintWriter;

/**
 * Wraps PrintWriter to provide convenient methods for Java source code generation
 */
public class CodeWriter {

  protected PrintWriter writer;
  protected CodeFormattingConfig config;
  protected int depth  = 0;

  public CodeWriter(PrintWriter writer, CodeFormattingConfig config) {
    this.writer = writer;
    this.config = config;
  }

  public CodeWriter tab() {
    return print(config.getTab());
  }

  public CodeWriter tabs(int depth) {
    while (depth-- > 0) tab();
    return this;
  }

  public CodeWriter eol() {
    return print(config.getEol());
  }

  public CodeWriter eos() {
    return print(";");
  }

  public CodeWriter eosl() {
    return print(";").eol();
  }

  public CodeWriter lineStatement(String line) {
    return print(line).eosl();
  }

  public CodeWriter blockStart() {
    writer.print(" {");
    depth++;
    return eol();
  }

  public CodeWriter blockEnd() {
    depth--;
    tabs(depth);
    writer.print("}");
    return this;
  }

  public CodeWriter indent() {
    return tabs(depth);
  }

  public CodeWriter print(String s) {
    writer.print(s);
    return this;
  }

  public CodeWriter println() {
    return eol();
  }

  public CodeWriter println(String x) {
    return print(x).eol();
  }

  public PrintWriter format(String format, Object... args) {
    return writer.format(format, args);
  }

  public CodeWriter flush() {
    writer.flush();
    return this;
  }

  public void close() {
    writer.close();
  }
}
