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

        BooleanParser booleanParser = new BooleanParser("", "a");
        Assert.assertFalse(booleanParser.parse(settings, false));

        Assert.assertEquals(Long.valueOf(1), Settings.item.number.parse(settings));
        Assert.assertEquals(Long.valueOf(20), Settings.item.number.parse(settings, 20L));
        settings.put("item.number", "10");
        Assert.assertEquals(Long.valueOf(10), Settings.item.number.parse(settings, 20L));
    }

    public static class Settings {

        public static final ItemParser item = new ItemParser();
    }

    public static class ItemParser extends VoidParser {

        public final NumberParser number = new NumberParser("item.");

        public ItemParser() {
            super("", "item");
        }
    }

    public static class NumberParser extends LongParser {

        public NumberParser(String prefix) {
            super(prefix, "number", 1L);
        }
    }
}