package com.github.sigalhu.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019-05-08
 */
public class AnnotationReflectUtilsTest {

    @Test
    public void updateAttribute() throws Exception {
        TestAnnotation annotation = TestClass.class.getAnnotation(TestAnnotation.class);
        Assert.assertEquals("class", annotation.value());
        AnnotationReflectUtils.updateAttribute(annotation, "value", "123");
        Assert.assertEquals("123", annotation.value());

        annotation = TestClass.class.getDeclaredField("testField").getAnnotation(TestAnnotation.class);
        Assert.assertEquals("field", annotation.value());
        AnnotationReflectUtils.updateAttribute(annotation, "value", "321");
        Assert.assertEquals("321", annotation.value());
    }

    @Test
    public void updateAttributes() throws Exception {
        TestAnnotation annotation = TestClass.class.getAnnotation(TestAnnotation.class);
        Assert.assertEquals("class", annotation.value());
        AnnotationReflectUtils.updateAttributes(annotation, new HashMap<String, Object>(){
            {
                put("value", "123");
            }
        });
        Assert.assertEquals("123", annotation.value());

        annotation = TestClass.class.getDeclaredField("testField").getAnnotation(TestAnnotation.class);
        Assert.assertEquals("field", annotation.value());
        AnnotationReflectUtils.updateAttributes(annotation, new HashMap<String, Object>(){
            {
                put("value", "321");
            }
        });
        Assert.assertEquals("321", annotation.value());
    }

    @TestAnnotation("class")
    private class TestClass {

        @TestAnnotation("field")
        private String testField;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    private @interface TestAnnotation {
        String value();
    }
}