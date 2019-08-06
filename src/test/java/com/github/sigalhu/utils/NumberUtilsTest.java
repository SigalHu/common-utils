package com.github.sigalhu.utils;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

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
        @SuppressWarnings("unchecked")
        List<Long> list = (List<Long>) NumberUtils.tryConvertTo64Bits(Lists.newArrayList(
                Lists.newArrayList(1,2,3),
                Lists.newArrayList(4F,5F,6F)
        ));
        assertArrayEquals(new Object[]{
                Lists.newArrayList(1L,2L,3L),
                Lists.newArrayList(4D,5D,6D)
        }, list.toArray());
    }
}