package ee.icefire.modelgen.java.code;

import java.io.File;

public class JavaCodeGeneratorConfig {

  protected String directory;
  protected CodeFormattingConfig codeFormattingConfig = new CodeFormattingConfig();
  protected boolean includeClassComments;
  protected boolean includeFieldComments;

  public String getDirectory() {
    return directory;
  }

  public void setDirectory(String directory) {
    this.directory = directory;
    if (!this.directory.endsWith(File.separator)) {
      this.directory += File.separator;
    }
  }

  public CodeFormattingConfig getCodeFormattingConfig() {
    return codeFormattingConfig;
  }

  public void setCodeFormattingConfig(CodeFormattingConfig codeFormattingConfig) {
    this.codeFormattingConfig = codeFormattingConfig;
  }

  public boolean isIncludeClassComments() {
    return includeClassComments;
  }

  public void setIncludeClassComments(boolean includeClassComments) {
    this.includeClassComments = includeClassComments;
  }

  public boolean isIncludeFieldComments() {
    return includeFieldComments;
  }

  public void setIncludeFieldComments(boolean includeFieldComments) {
    this.includeFieldComments = includeFieldComments;
  }
}
