package com.github.sigalhu.utils;

import org.junit.Test;

import java.util.*;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public class AssertTest {

    @Test
    public void isTrue() {
        Assert.isTrue(true, () -> new TestException(10000, "The expression must be true!"));
        Assert.isTrue(true, "The expression must be true!");
        Assert.isTrue(true, () -> "The expression must be true!");
        TestAssert.checker.isTrue(true, 10000L);
        TestAssert.checker.isTrue(true, () -> 10000L);
        TestAssert.checker.isTrue(true, 10000L, "The expression must be true!");
        TestAssert.checker.isTrue(true, 10000L, () -> "The expression must be true!");
    }

    @Test
    public void isTrueWithException() {
        try {
            Assert.isTrue(false, () -> new TestException(10000, "The expression must be true!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.isTrue(false, "The expression must be true!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.isTrue(false, () -> "The expression must be true!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.isTrue(false, 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isTrue(false, () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isTrue(false, 10000L, "The expression must be true!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isTrue(false, 10000L, () -> "The expression must be true!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void isFalse() {
        Assert.isFalse(false, () -> new TestException(10000, "The expression must be false!"));
        Assert.isFalse(false, "The expression must be false!");
        Assert.isFalse(false, () -> "The expression must be false!");
        TestAssert.checker.isFalse(false, 10000L);
        TestAssert.checker.isFalse(false, () -> 10000L);
        TestAssert.checker.isFalse(false, 10000L, "The expression must be false!");
        TestAssert.checker.isFalse(false, 10000L, () -> "The expression must be false!");
    }

    @Test
    public void isFalseWithException() {
        try {
            Assert.isFalse(true, () -> new TestException(10000, "The expression must be false!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.isFalse(true, "The expression must be false!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.isFalse(true, () -> "The expression must be false!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.isFalse(true, 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isFalse(true, () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isFalse(true, 10000L, "The expression must be false!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isFalse(true, 10000L, () -> "The expression must be false!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void isNull() {
        Assert.isNull(null, () -> new TestException(10000, "The object must be null!"));
        Assert.isNull(null, "The object must be null!");
        Assert.isNull(null, () -> "The object must be null!");
        TestAssert.checker.isNull(null, 10000L);
        TestAssert.checker.isNull(null, () -> 10000L);
        TestAssert.checker.isNull(null, 10000L, "The object must be null!");
        TestAssert.checker.isNull(null, 10000L, () -> "The object must be null!");
    }

    @Test
    public void isNullWithException() {
        try {
            Assert.isNull(0, () -> new TestException(10000, "The object must be null!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.isNull(0, "The object must be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.isNull(0, () -> "The object must be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.isNull(0, 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isNull(0, () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isNull(0, 10000L, "The object must be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.isNull(0, 10000L, () -> "The object must be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void notNull() {
        Assert.notNull(0, () -> new TestException(10000, "The object must not be null!"));
        Assert.notNull(0, "The object must not be null!");
        Assert.notNull(0, () -> "The object must not be null!");
        TestAssert.checker.notNull(0, 10000L);
        TestAssert.checker.notNull(0, () -> 10000L);
        TestAssert.checker.notNull(0, 10000L, "The object must not be null!");
        TestAssert.checker.notNull(0, 10000L, () -> "The object must not be null!");
    }

    @Test
    public void notNullWithException() {
        try {
            Assert.notNull(null, () -> new TestException(10000, "The object must not be null!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.notNull(null, "The object must not be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.notNull(null, () -> "The object must not be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.notNull(null, 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notNull(null, () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notNull(null, 10000L, "The object must not be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notNull(null, 10000L, () -> "The object must not be null!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void hasLength() {
        Assert.hasLength(" ", () -> new TestException(10000, "The text must have length!"));
        Assert.hasLength(" ", "The text must have length!");
        Assert.hasLength(" ", () -> "The text must have length!");
        TestAssert.checker.hasLength(" ", 10000L);
        TestAssert.checker.hasLength(" ", () -> 10000L);
        TestAssert.checker.hasLength(" ", 10000L, "The text must have length!");
        TestAssert.checker.hasLength(" ", 10000L, () -> "The text must have length!");
    }

    @Test
    public void hasLengthWithException() {
        try {
            Assert.hasLength("", () -> new TestException(10000, "The text must have length!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.hasLength("", "The text must have length!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.hasLength("", () -> "The text must have length!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.hasLength("", 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasLength("", () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasLength("", 10000L, "The text must have length!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasLength("", 10000L, () -> "The text must have length!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void hasText() {
        Assert.hasText("text", () -> new TestException(10000, "The text must have text!"));
        Assert.hasText("text", "The text must have text!");
        Assert.hasText("text", () -> "The text must have text!");
        TestAssert.checker.hasText("text", 10000L);
        TestAssert.checker.hasText("text", () -> 10000L);
        TestAssert.checker.hasText("text", 10000L, "The text must have text!");
        TestAssert.checker.hasText("text", 10000L, () -> "The text must have text!");
    }

    @Test
    public void hasTextWithException() {
        try {
            Assert.hasText(" ", () -> new TestException(10000, "The text must have text!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.hasText(" ", "The text must have text!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.hasText(" ", () -> "The text must have text!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.hasText(" ", 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasText(" ", () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasText(" ", 10000L, "The text must have text!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.hasText(" ", 10000L, () -> "The text must have text!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    @Test
    public void notEmpty() {
        Object[] array = new Object[]{0};
        Assert.notEmpty(array, () -> new TestException(10000, "The array must be not empty!"));
        Assert.notEmpty(array, "The array must be not empty!");
        Assert.notEmpty(array, () -> "The array must be not empty!");
        TestAssert.checker.notEmpty(array, 10000L);
        TestAssert.checker.notEmpty(array, () -> 10000L);
        TestAssert.checker.notEmpty(array, 10000L, "The array must be not empty!");
        TestAssert.checker.notEmpty(array, 10000L, () -> "The array must be not empty!");

        Collection<Object> collection = new ArrayList<>();
        collection.add(0);
        Assert.notEmpty(collection, () -> new TestException(10000, "The collection must be not empty!"));
        Assert.notEmpty(collection, "The collection must be not empty!");
        Assert.notEmpty(collection, () -> "The collection must be not empty!");
        TestAssert.checker.notEmpty(collection, 10000L);
        TestAssert.checker.notEmpty(collection, () -> 10000L);
        TestAssert.checker.notEmpty(collection, 10000L, "The collection must be not empty!");
        TestAssert.checker.notEmpty(collection, 10000L, () -> "The collection must be not empty!");

        Map<Object, Object> map = new HashMap<>();
        map.put(0, 0);
        Assert.notEmpty(map, () -> new TestException(10000, "The map must be not empty!"));
        Assert.notEmpty(map, "The map must be not empty!");
        Assert.notEmpty(map, () -> "The map must be not empty!");
        TestAssert.checker.notEmpty(map, 10000L);
        TestAssert.checker.notEmpty(map, () -> 10000L);
        TestAssert.checker.notEmpty(map, 10000L, "The map must be not empty!");
        TestAssert.checker.notEmpty(map, 10000L, () -> "The map must be not empty!");
    }

    @Test
    public void notEmptyWithException() {
        // Test Array
        try {
            Assert.notEmpty(Collections.emptyList().toArray(), () -> new TestException(10000, "The array must be not empty!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.notEmpty(Collections.emptyList().toArray(), "The array must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.notEmpty(Collections.emptyList().toArray(), () -> "The array must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList().toArray(), 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList().toArray(), () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList().toArray(), 10000L, "The array must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList().toArray(), 10000L, () -> "The array must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        // Test Collection
        try {
            Assert.notEmpty(Collections.emptyList(), () -> new TestException(10000, "The collection must be not empty!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.notEmpty(Collections.emptyList(), "The collection must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.notEmpty(Collections.emptyList(), () -> "The collection must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList(), 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList(), () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList(), 10000L, "The collection must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyList(), 10000L, () -> "The collection must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        // Test Map
        try {
            Assert.notEmpty(Collections.emptyMap(), () -> new TestException(10000, "The map must be not empty!"));
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            Assert.notEmpty(Collections.emptyMap(), "The map must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            Assert.notEmpty(Collections.emptyMap(), () -> "The map must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyMap(), 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyMap(), () -> 10000L);
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyMap(), 10000L, "The map must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }

        try {
            TestAssert.checker.notEmpty(Collections.emptyMap(), 10000L, () -> "The map must be not empty!");
            throw new Exception();
        } catch (Exception e) {
            org.junit.Assert.assertTrue(e instanceof TestException);
        }
    }

    private static class TestAssert {
        public static final Assert checker = new Assert((code, message) -> new TestException(code.intValue(), message));
    }

    private static class TestException extends RuntimeException {

        private int code;

        public TestException(int code, String message) {
            super(message);
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}