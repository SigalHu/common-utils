package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.utils.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Byte.class, byte.class})
public class ByteParser extends BaseSettingParser<Byte> {

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
}
