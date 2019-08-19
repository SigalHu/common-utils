package com.github.sigalhu.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author huxujun
 * @date 2019-08-19
 */
public class ArrayUtilsTest {

    @Test
    public void toObject() {
        Integer[] result = (Integer[]) ArrayUtils.toObject(new int[]{1, 2, 3});
        assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test(expected = ClassCastException.class)
    public void toObjectThrowClassCastException() {
        Object[] b = ArrayUtils.toObject(1);
    }

    @Test
    public void asList() {
        List<Object> result = ArrayUtils.asList(new int[]{1, 2, 3});
        assertArrayEquals(new Integer[]{1, 2, 3}, result.toArray());
    }

    @Test(expected = ClassCastException.class)
    public void asListThrowClassCastException() {
        List<Object> result = ArrayUtils.asList(1);
    }
}