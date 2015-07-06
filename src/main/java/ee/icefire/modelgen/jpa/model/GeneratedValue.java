package ee.icefire.modelgen.jpa.model;

public class GeneratedValue {

    protected String generator;
    protected boolean sequenceStrategy;
    protected String sequenceName;
    protected Integer sequenceAllocationSize;

    public GeneratedValue(String generator) {
        this.generator = generator;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public boolean isSequenceStrategy() {
        return sequenceStrategy;
    }

    public void setSequenceStrategy(boolean sequenceStrategy) {
        this.sequenceStrategy = sequenceStrategy;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Integer getSequenceAllocationSize() {
        return sequenceAllocationSize;
    }

    public void setSequenceAllocationSize(Integer sequenceAllocationSize) {
        this.sequenceAllocationSize = sequenceAllocationSize;
    }
}
