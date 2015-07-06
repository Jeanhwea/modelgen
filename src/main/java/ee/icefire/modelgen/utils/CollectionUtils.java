package ee.icefire.modelgen.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {
    
    public static <T> List<T> unmodifiableMerge(List<T> l1, List<T> l2) {
        return Collections.unmodifiableList(merge(l1, l2));
    }
    
    public static <T> List<T> merge(List<T> l1, List<T> l2) {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(l1);
        result.addAll(l2);
        return result;
    }
    
}
