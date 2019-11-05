package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.utils.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Long.class, long.class})
public class LongParser extends BaseSettingParser<Long> {

    public LongParser(String setting) {
        this(null, setting);
    }

    public LongParser(String setting, Long defaultValue) {
        this(null, setting, defaultValue);
    }

    public LongParser(String prefix, String setting) {
        this(prefix, setting, 0L);
    }

    public LongParser(String prefix, String setting, Long defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Long parseString(String str, Long defaultValue) {
        return NumberUtils.toLong(str, defaultValue);
    }
}
