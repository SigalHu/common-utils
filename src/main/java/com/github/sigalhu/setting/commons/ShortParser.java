package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.utils.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Short.class, short.class})
public class ShortParser extends BaseSettingParser<Short> {

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
}
