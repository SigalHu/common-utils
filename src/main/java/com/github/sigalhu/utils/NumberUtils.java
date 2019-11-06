package com.github.sigalhu.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 比较数据类型的数值大小
     *
     * @param left  左值
     * @param right 右值
     * @return
     */
    public static <L extends Number, R extends Number> int compare(L left, R right) {
        if (left == null || right == null) {
            if (left == right) {
                return 0;
            }
            throw new NullPointerException();
        }
        if (left instanceof Float || left instanceof Double
                || right instanceof Float || right instanceof Double) {
            return Objects.compare(left.doubleValue(), right.doubleValue(), Comparator.comparing(Function.identity()));
        }
        return Objects.compare(left.longValue(), right.longValue(), Comparator.comparing(Function.identity()));
    }

    /**
     * 判断是否相等，当为数据类型时，比较数值是否相等
     *
     * @param a 左值
     * @param b 右值
     * @return
     */
    public static boolean equals(Object a, Object b) {
        if (a instanceof Number && b instanceof Number) {
            return compare((Number) a, (Number) b) == 0;
        }
        return Objects.equals(a, b);
    }

    /**
     * 将数据类型的数值转换为 64 bits
     *
     * @param object 数值
     * @return
     */
    public static Object tryConvertTo64Bits(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Number) {
            Number number = (Number) object;
            if (number instanceof Float || number instanceof Double) {
                return number.doubleValue();
            }
            return number.longValue();
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            CollectionUtils.transform(collection, NumberUtils::tryConvertTo64Bits);
            return collection;
        }
        return object;
    }

    /**
     * 将字符串解析为 {@link Integer}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Integer toInteger(final String str, final Integer defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串解析为 {@link Long}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Long toLong(final String str, final Long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串解析为 {@link Float}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Float toFloat(final String str, final Float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串解析为 {@link Double}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Double toDouble(final String str, final Double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串解析为 {@link Byte}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Byte toByte(final String str, final Byte defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串解析为 {@link Short}
     *
     * @param str 待解析的字符串
     * @param defaultValue 默认值
     * @return
     */
    public static Short toShort(final String str, final Short defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
}
