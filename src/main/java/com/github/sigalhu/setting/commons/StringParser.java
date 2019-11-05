package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class StringParser extends BaseSettingParser<String> implements AdaptedTypeReporter {

    public StringParser(String setting) {
        this(null, setting);
    }

    public StringParser(String setting, Object defaultValue) {
        this(null, setting, String.valueOf(defaultValue));
    }

    public StringParser(String prefix, String setting) {
        this(prefix, setting, "");
    }

    public StringParser(String prefix, String setting, Object defaultValue) {
        super(prefix, setting, String.valueOf(defaultValue));
    }

    @Override
    protected String parseString(String str, String defaultValue) {
        return Objects.nonNull(str) ? str : defaultValue;
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{String.class};
    }
}
