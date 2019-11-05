package com.github.sigalhu.setting.commons;


import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import com.github.sigalhu.setting.reporters.SupplerReporter;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class StringParser extends BaseSettingParser<String> implements AdaptedTypeReporter, SupplerReporter {

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

    @Override
    public Function<String, ? extends SettingParser> parserSuppler() {
        return StringParser::new;
    }
}
