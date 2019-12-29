package com.github.sigalhu.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author huxujun
 * @date 2019/12/29
 */
public class CompressUtilsTest {

    private static String str = RandomStringUtils.randomNumeric(1000);

    @Test
    public void zip() {
        byte[] bytes = CompressUtils.zip(str);
        String result = CompressUtils.unzip2Str(bytes);
        Assert.assertEquals(str, result);

        System.out.println(str.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(bytes.length);
    }

    @Test
    public void zipAndEncode() {
        String compressString = CompressUtils.zipAndEncode(str);
        String result = CompressUtils.decodeAndUnzip2Str(compressString);
        Assert.assertEquals(str, result);

        System.out.println(str.length());
        System.out.println(compressString.length());
    }
}