package com.github.sigalhu.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/10/31
 */
public class JvmUtilsTest {

    @Test
    public void test() {
        long gcCount = JvmUtils.getGcCount();
        JvmUtils.triggerGc(10);
        Assert.assertTrue(JvmUtils.getGcCount() >= gcCount + 10);
    }
}