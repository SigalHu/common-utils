package com.github.sigalhu.setting.commons;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class ByteParser extends BaseSettingParser<Byte> implements AdaptedTypeReporter {

    public ByteParser(String setting) {
        this(null, setting);
    }

    public ByteParser(String setting, Byte defaultValue) {
        this(null, setting, defaultValue);
    }

    public ByteParser(String prefix, String setting) {
        this(prefix, setting, (byte) 0);
    }

    public ByteParser(String prefix, String setting, Byte defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Byte parseString(String str, Byte defaultValue) {
        return NumberUtils.toByte(str, defaultValue);
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Byte.class, byte.class};
    }
}
