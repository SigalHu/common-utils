package com.github.sigalhu.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public class Assert {

    public static void isTrue(boolean expression, Supplier<RuntimeException> supplier) {
        if (!expression) {
            throw supplier.get();
        }
    }

    public static void isFalse(boolean expression, Supplier<RuntimeException> supplier) {
        if (expression) {
            throw supplier.get();
        }
    }

    public static void isNull(Object object, Supplier<RuntimeException> supplier) {
        if (object != null) {
            throw supplier.get();
        }
    }

    public static void notNull(Object object, Supplier<RuntimeException> supplier) {
        if (object == null) {
            throw supplier.get();
        }
    }

    public static void hasLength(String text, Supplier<RuntimeException> supplier) {
        if (text == null || text.isEmpty()) {
            throw supplier.get();
        }
    }

    public static void hasText(String text, Supplier<RuntimeException> supplier) {
        if (text == null || text.isEmpty() || !containsText(text)) {
            throw supplier.get();
        }
    }

    public static void notEmpty(Object[] array, Supplier<RuntimeException> supplier) {
        if (array == null || array.length == 0) {
            throw supplier.get();
        }
    }

    public static void notEmpty(Collection<?> collection, Supplier<RuntimeException> supplier) {
        if (collection == null || collection.isEmpty()) {
            throw supplier.get();
        }
    }

    public static void notEmpty(Map<?, ?> map, Supplier<RuntimeException> supplier) {
        if (map == null || map.isEmpty()) {
            throw supplier.get();
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
