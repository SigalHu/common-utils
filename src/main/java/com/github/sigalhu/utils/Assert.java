package com.github.sigalhu.utils;

import com.github.sigalhu.function.base.StringSupplier;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public class Assert {

    private BiFunction<Long, String, RuntimeException> exceptionGenerator;

    public Assert(BiFunction<Long, String, RuntimeException> exceptionGenerator) {
        this.exceptionGenerator = exceptionGenerator;
    }

    public void isTrue(boolean expression, Long code) {
        if (!expression) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void isTrue(boolean expression, LongSupplier codeSupplier) {
        if (!expression) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void isTrue(boolean expression, Long code, String message) {
        if (!expression) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void isTrue(boolean expression, Long code, StringSupplier messageSupplier) {
        if (!expression) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, StringSupplier messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void isTrue(boolean expression, Supplier<RuntimeException> exceptionSupplier) {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    public void isFalse(boolean expression, Long code) {
        if (expression) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void isFalse(boolean expression, LongSupplier codeSupplier) {
        if (expression) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void isFalse(boolean expression, Long code, String message) {
        if (expression) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void isFalse(boolean expression, Long code, StringSupplier messageSupplier) {
        if (expression) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean expression, StringSupplier messageSupplier) {
        if (expression) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void isFalse(boolean expression, Supplier<RuntimeException> exceptionSupplier) {
        if (expression) {
            throw exceptionSupplier.get();
        }
    }

    public void isNull(Object object, Long code) {
        if (object != null) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void isNull(Object object, LongSupplier codeSupplier) {
        if (object != null) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void isNull(Object object, Long code, String message) {
        if (object != null) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void isNull(Object object, Long code, StringSupplier messageSupplier) {
        if (object != null) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, StringSupplier messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void isNull(Object object, Supplier<RuntimeException> exceptionSupplier) {
        if (object != null) {
            throw exceptionSupplier.get();
        }
    }

    public void notNull(Object object, Long code) {
        if (object == null) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void notNull(Object object, LongSupplier codeSupplier) {
        if (object == null) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void notNull(Object object, Long code, String message) {
        if (object == null) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void notNull(Object object, Long code, StringSupplier messageSupplier) {
        if (object == null) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, StringSupplier messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void notNull(Object object, Supplier<RuntimeException> exceptionSupplier) {
        if (object == null) {
            throw exceptionSupplier.get();
        }
    }

    public void hasLength(String text, Long code) {
        if (text == null || text.isEmpty()) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void hasLength(String text, LongSupplier codeSupplier) {
        if (text == null || text.isEmpty()) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void hasLength(String text, Long code, String message) {
        if (text == null || text.isEmpty()) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void hasLength(String text, Long code, StringSupplier messageSupplier) {
        if (text == null || text.isEmpty()) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void hasLength(String text, String message) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(String text, StringSupplier messageSupplier) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void hasLength(String text, Supplier<RuntimeException> exceptionSupplier) {
        if (text == null || text.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }

    public void hasText(String text, Long code) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void hasText(String text, LongSupplier codeSupplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void hasText(String text, Long code, String message) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void hasText(String text, Long code, StringSupplier messageSupplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void hasText(String text, String message) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(String text, StringSupplier messageSupplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void hasText(String text, Supplier<RuntimeException> exceptionSupplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw exceptionSupplier.get();
        }
    }

    public void notEmpty(Object[] array, Long code) {
        if (array == null || array.length == 0) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void notEmpty(Object[] array, LongSupplier codeSupplier) {
        if (array == null || array.length == 0) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void notEmpty(Object[] array, Long code, String message) {
        if (array == null || array.length == 0) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void notEmpty(Object[] array, Long code, StringSupplier messageSupplier) {
        if (array == null || array.length == 0) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, StringSupplier messageSupplier) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void notEmpty(Object[] array, Supplier<RuntimeException> exceptionSupplier) {
        if (array == null || array.length == 0) {
            throw exceptionSupplier.get();
        }
    }

    public void notEmpty(Collection<?> collection, Long code) {
        if (collection == null || collection.isEmpty()) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void notEmpty(Collection<?> collection, LongSupplier codeSupplier) {
        if (collection == null || collection.isEmpty()) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void notEmpty(Collection<?> collection, Long code, String message) {
        if (collection == null || collection.isEmpty()) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void notEmpty(Collection<?> collection, Long code, StringSupplier messageSupplier) {
        if (collection == null || collection.isEmpty()) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, StringSupplier messageSupplier) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void notEmpty(Collection<?> collection, Supplier<RuntimeException> exceptionSupplier) {
        if (collection == null || collection.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }

    public void notEmpty(Map<?, ?> map, Long code) {
        if (map == null || map.isEmpty()) {
            throw exceptionGenerator.apply(code, null);
        }
    }

    public void notEmpty(Map<?, ?> map, LongSupplier codeSupplier) {
        if (map == null || map.isEmpty()) {
            throw exceptionGenerator.apply(codeSupplier.getAsLong(), null);
        }
    }

    public void notEmpty(Map<?, ?> map, Long code, String message) {
        if (map == null || map.isEmpty()) {
            throw exceptionGenerator.apply(code, message);
        }
    }

    public void notEmpty(Map<?, ?> map, Long code, StringSupplier messageSupplier) {
        if (map == null || map.isEmpty()) {
            throw exceptionGenerator.apply(code, messageSupplier.get());
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map, StringSupplier messageSupplier) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    public static void notEmpty(Map<?, ?> map, Supplier<RuntimeException> exceptionSupplier) {
        if (map == null || map.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
