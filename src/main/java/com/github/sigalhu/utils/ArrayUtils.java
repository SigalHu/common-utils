package com.github.sigalhu.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-08-18
 */
public class ArrayUtils {

    /**
     * 将数组对象转化为 {@link Object} 数组
     *
     * @param array
     * @return
     */
    public static Object[] toObject(Object array) {
        if (array instanceof boolean[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((boolean[]) array);
        } else if (array instanceof char[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((char[]) array);
        } else if (array instanceof byte[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((byte[]) array);
        } else if (array instanceof short[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((short[]) array);
        } else if (array instanceof int[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((int[]) array);
        } else if (array instanceof long[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((long[]) array);
        } else if (array instanceof float[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((float[]) array);
        } else if (array instanceof double[]) {
            return org.apache.commons.lang3.ArrayUtils.toObject((double[]) array);
        }
        return (Object[]) array;
    }

    /**
     * 将数组对象转化为固定大小的列表
     *
     * @param array
     * @return
     */
    public static List<Object> asList(Object array) {
        return Arrays.asList(toObject(array));
    }
}
