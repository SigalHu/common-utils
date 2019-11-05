package com.github.sigalhu.setting.commons;

/**
 * @author huxujun
 * @date 2019/11/3
 */
public class VoidParser extends BaseSettingParser<Void> {

    public VoidParser(String setting) {
        super(setting);
    }

    public VoidParser(String prefix, String setting) {
        super(prefix, setting);
    }

    @Override
    protected Void parseString(String str, Void defaultValue) {
        return defaultValue;
    }
}
