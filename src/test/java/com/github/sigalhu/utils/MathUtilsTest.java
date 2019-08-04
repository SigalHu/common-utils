package com.github.sigalhu.utils;

import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void equals() {
        assertFalse(MathUtils.equals(1, "1"));
        assertTrue(MathUtils.equals(1L, 1D));
        assertFalse(MathUtils.equals(2, 1L));
        assertTrue(MathUtils.equals(1F, 1D));
        assertFalse(MathUtils.equals(null, 1D));
        assertFalse(MathUtils.equals(1L, null));
        assertTrue(MathUtils.equals(null, null));
    }
}