package com.github.sigalhu.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author huxujun
 * @date 2019-08-04
 */
public class MathUtilsTest {

    @Test
    public void compare() {
        assertEquals(0, MathUtils.compare(1L, 1D));
        assertEquals(-1, MathUtils.compare(1, 2L));
        assertEquals(1, MathUtils.compare(2D, 1));
        assertEquals(0, MathUtils.compare(null, null));
    }
}