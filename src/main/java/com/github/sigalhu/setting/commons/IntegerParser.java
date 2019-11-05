package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class IntegerParser extends BaseSettingParser<Integer> implements AdaptedTypeReporter {

    public IntegerParser(String setting) {
        this(null, setting);
    }

    public IntegerParser(String setting, Integer defaultValue) {
        this(null, setting, defaultValue);
    }

    public IntegerParser(String prefix, String setting) {
        this(prefix, setting, 0);
    }

    public IntegerParser(String prefix, String setting, Integer defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Integer parseString(String str, Integer defaultValue) {
        return NumberUtils.toInt(str, defaultValue);
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Integer.class, int.class};
    }
}
