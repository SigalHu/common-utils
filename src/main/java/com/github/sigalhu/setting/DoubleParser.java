package com.github.sigalhu.setting;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class DoubleParser extends BaseSettingParser<Double> {

    public DoubleParser(String prefix, String setting) {
        super(prefix, setting, 0D);
    }

    public DoubleParser(String prefix, String setting, Double defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Double parseString(String str, Double defaultValue) {
        return NumberUtils.toDouble(str, defaultValue);
    }
}
