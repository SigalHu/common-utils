package com.github.sigalhu.setting.commons;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class ShortParser extends BaseSettingParser<Short> implements AdaptedTypeReporter {

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
}
