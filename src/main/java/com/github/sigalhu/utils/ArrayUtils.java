package com.github.sigalhu.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-08-18
 */
public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    /**
     * 将包括基本数据类型在内的数组对象转化为 {@link Object} 数组
     *
     * @param array
     * @return
     */
    public static Object[] toObject(Object array) {
        if (array instanceof boolean[]) {
            return toObject((boolean[]) array);
        } else if (array instanceof char[]) {
            return toObject((char[]) array);
        } else if (array instanceof byte[]) {
            return toObject((byte[]) array);
        } else if (array instanceof short[]) {
            return toObject((short[]) array);
        } else if (array instanceof int[]) {
            return toObject((int[]) array);
        } else if (array instanceof long[]) {
            return toObject((long[]) array);
        } else if (array instanceof float[]) {
            return toObject((float[]) array);
        } else if (array instanceof double[]) {
            return toObject((double[]) array);
        }
        return (Object[]) array;
    }

    /**
     * 将包括基本数据类型在内的数组对象转化为固定大小的列表
     *
     * @param array
     * @return
     */
    public static List<Object> asList(Object array) {
        return Arrays.asList(toObject(array));
    }
}
