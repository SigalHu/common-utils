package com.github.sigalhu.setting;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class LongParser extends BaseSettingParser<Long> {

    public LongParser(String prefix, String setting) {
        super(prefix, setting, 0L);
    }

    public LongParser(String prefix, String setting, Long defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Long parseString(String str, Long defaultValue) {
        return NumberUtils.toLong(str, defaultValue);
    }
}
