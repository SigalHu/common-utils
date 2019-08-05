package com.github.sigalhu.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019-08-04
 */
public class NumberUtilsTest {

    @Test
    public void compare() {
        assertEquals(0, NumberUtils.compare(1L, 1D));
        assertEquals(-1, NumberUtils.compare(1, 2L));
        assertEquals(1, NumberUtils.compare(2D, 1));
        assertEquals(0, NumberUtils.compare(null, null));
    }

    @Test
    public void equals() {
        assertFalse(NumberUtils.equals(1, "1"));
        assertTrue(NumberUtils.equals(1L, 1D));
        assertFalse(NumberUtils.equals(2, 1L));
        assertTrue(NumberUtils.equals(1F, 1D));
        assertFalse(NumberUtils.equals(null, 1D));
        assertFalse(NumberUtils.equals(1L, null));
        assertTrue(NumberUtils.equals(null, null));
    }

    @Test
    public void convertTo64Bits() {
        assertEquals(1L, NumberUtils.tryConvertTo64Bits(1));
        assertEquals(1D, NumberUtils.tryConvertTo64Bits(1F));
    }
}