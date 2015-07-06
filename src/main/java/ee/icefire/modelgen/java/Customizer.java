package ee.icefire.modelgen.java;


@FunctionalInterface
public interface Customizer<T> {

    public void customize(T t);

}
