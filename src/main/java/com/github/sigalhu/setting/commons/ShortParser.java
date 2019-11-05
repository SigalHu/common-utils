package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import com.github.sigalhu.setting.reporters.SupplerReporter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class ShortParser extends BaseSettingParser<Short> implements AdaptedTypeReporter, SupplerReporter {

    public ShortParser(String setting) {
        this(null, setting);
    }

    public ShortParser(String setting, Short defaultValue) {
        this(null, setting, defaultValue);
    }

    public ShortParser(String prefix, String setting) {
        this(prefix, setting, (short) 0);
    }

    public ShortParser(String prefix, String setting, Short defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Short parseString(String str, Short defaultValue) {
        return NumberUtils.toShort(str, defaultValue);
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Short.class, short.class};
    }

    @Override
    public Function<String, ? extends SettingParser> parserSuppler() {
        return ShortParser::new;
    }
}
