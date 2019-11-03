package com.github.sigalhu.setting;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class DummyParser extends BaseSettingParser {

    public DummyParser(String setting) {
        this(null, setting);
    }

    public DummyParser(String prefix, String setting) {
        this(prefix, setting, null);
    }

    public DummyParser(String prefix, String setting, Object defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Object parseString(String str, Object defaultValue) {
        return defaultValue;
    }
}
