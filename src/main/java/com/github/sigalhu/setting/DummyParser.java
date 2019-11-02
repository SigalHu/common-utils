package com.github.sigalhu.setting;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class DummyParser extends BaseSettingParser {

    public DummyParser(String prefix, String setting) {
        super(prefix, setting);
    }

    @Override
    protected Object parseString(String str, Object defaultValue) {
        return defaultValue;
    }
}
