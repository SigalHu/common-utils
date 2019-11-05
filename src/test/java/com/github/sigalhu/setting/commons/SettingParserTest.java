package com.github.sigalhu.setting.commons;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class SettingParserTest {

    @Test
    public void parse() {
        Map<String, String> settings = new HashMap<>();
        settings.put("a.b", "true");

        BooleanParser booleanParser = new BooleanParser("a");
        Assert.assertFalse(booleanParser.parse(settings, false));

        LongParser longParser = new LongParser("item.number", 1L);
        Assert.assertEquals(Long.valueOf(1), longParser.parse(settings));
        Assert.assertEquals(Long.valueOf(20), longParser.parse(settings, 20L));
        settings.put("item.number", "10");
        Assert.assertEquals(Long.valueOf(10), longParser.parse(settings, 20L));
    }
}