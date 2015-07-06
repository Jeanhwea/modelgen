package ee.icefire.modelgen.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ee.icefire.modelgen.java.ClassUtils.extractClass;
import static ee.icefire.modelgen.java.ClassUtils.extractPackage;
import static ee.icefire.modelgen.java.ClassUtils.isQualifiedClassName;

public class ClassUtilsTest {

    @Test
    public void testIsQualifiedClassName() {
        assertTrue(isQualifiedClassName("java.math.BigDecimal"));
        assertTrue(isQualifiedClassName("org.example.package.Foo"));
        assertTrue(isQualifiedClassName("com.example._123name.$Bar"));
    }
    
    @Test
    public void testIsNotQualifiedClassName() {
        assertFalse(isQualifiedClassName("BigDecimal"));
        assertFalse(isQualifiedClassName("Test.Argument"));
        assertFalse(isQualifiedClassName("$Bar._Baz"));
    }
    
    @Test
    public void testExtractClassName() {
        assertEquals("BigDecimal", extractClass("java.math.BigDecimal"));
        assertEquals("String", extractClass("String"));
        assertEquals("$Bar", extractClass("com.example._123name.$Bar"));
        assertEquals("OuterFoo.NestedFoo", extractClass("org.example.OuterFoo.NestedFoo"));
    }
    
    @Test
    public void testExtractPackageName() {
        assertEquals("java.math", extractPackage("java.math.BigDecimal"));
        assertEquals("", extractPackage("String"));
        assertEquals("com.example._123name", extractPackage("com.example._123name.$Bar"));
        assertEquals("org.example", extractPackage("org.example.OuterFoo.NestedFoo"));
    }

}