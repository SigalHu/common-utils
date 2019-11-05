package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class LongParser extends BaseSettingParser<Long> implements AdaptedTypeReporter {

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

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Long.class, long.class};
    }
}
